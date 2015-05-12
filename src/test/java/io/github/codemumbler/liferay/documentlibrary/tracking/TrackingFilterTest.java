package io.github.codemumbler.liferay.documentlibrary.tracking;

import io.github.codemumbler.liferay.documentlibrary.MockHttpServletRequest;
import io.github.codemumbler.liferay.documentlibrary.MockLiferayUtil;
import io.github.codemumbler.liferay.documentlibrary.MockLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import java.io.IOException;
import java.util.UUID;

public class TrackingFilterTest {

	private MockLiferayUtil liferayUtil;
	private TrackingFilter filter;
	private MockHttpServletRequest httpServletRequest;
	private ServletRequest servletRequest;
	private UUID uuid;

	@Before
	public void setUp() throws Exception {
		uuid = UUID.randomUUID();
		liferayUtil = new MockLiferayUtil(true, uuid);
		filter = new TrackingFilter();
		servletRequest = new MockServletRequest();
		servletRequest.setAttribute("INVOKER_FILTER_URI", "/documents/1234/sample.pdf");

		httpServletRequest = new MockHttpServletRequest();
		httpServletRequest.getHeaders().put("user-agent", "JUnit Servlet Tests");
		httpServletRequest.setRequestURL("http://example.com/documents/1234/sample.pdf");
		httpServletRequest.setRemoteAddr("1.1.1.1");
		liferayUtil.setOriginalRequest(httpServletRequest);
	}

	@Test
	public void disabledTracking() throws IOException, ServletException {
		liferayUtil = new MockLiferayUtil(false, UUID.randomUUID());
		doFilter();
		Assert.assertEquals("[Document Library tracking currently disabled]", ((MockLog) liferayUtil.getLogger()).getMessages());
	}

	@Test
	public void trackedRegularPDF() throws Exception {
		doFilter();
		String expected = "[Requested URL: http://example.com/documents/1234/sample.pdf, Requesting User-Agent: JUnit Servlet Tests, Requesting IP: 1.1.1.1, Document Library tracking info: GoogleAnalytics{uuid='" + uuid.toString() + "',account='UA-101101-9', postedParameters='v=1&cid=" + uuid.toString() + "&sc=start&dh=example.com&t=pageview&dp=%2Fdocuments%2F1234%2Fsample.pdf&dt=title&tid=UA-101101-9&uip=1.1.1.1&ua=JUnit+Servlet+Tests'}]";
		Assert.assertEquals(expected, ((MockLog) liferayUtil.getLogger()).getMessages());
	}

	@Test
	public void badHostName() throws Exception {
		httpServletRequest.setRequestURL("/documents/1234/sample.pdf");
		doFilter();
		Assert.assertEquals("[Requested URL: /documents/1234/sample.pdf, Requesting User-Agent: JUnit Servlet Tests, Requesting IP: 1.1.1.1, Failed to track document library item :: Tracking using Google Analytics failed]", ((MockLog) liferayUtil.getLogger()).getMessages());
	}

	@Test
	public void xForwardedFor() throws Exception {
		httpServletRequest.getHeaders().put("x-forwarded-for", "2.2.2.2");
		doFilter();
		String expected = "[Requested URL: http://example.com/documents/1234/sample.pdf, Requesting User-Agent: JUnit Servlet Tests, Requesting IP: 2.2.2.2, Document Library tracking info: GoogleAnalytics{uuid='" + uuid.toString() + "',account='UA-101101-9', postedParameters='v=1&cid=" + uuid.toString() + "&sc=start&dh=example.com&t=pageview&dp=%2Fdocuments%2F1234%2Fsample.pdf&dt=title&tid=UA-101101-9&uip=2.2.2.2&ua=JUnit+Servlet+Tests'}]";
		Assert.assertEquals(expected, ((MockLog) liferayUtil.getLogger()).getMessages());
	}

	@Test
	public void brokenLink() throws Exception {
		liferayUtil.setThrowFileEntryException(true);
		doFilter();
		Assert.assertEquals("[The document failed to track because it doesn't exist.]", ((MockLog) liferayUtil.getLogger()).getMessages());
	}

	@Test
	public void doNotTrackNonPDFs() throws Exception {
		servletRequest.setAttribute("INVOKER_FILTER_URI", "/documents/1234/sample.jpg");
		httpServletRequest.setRequestURL("/documents/1234/sample.jpg");
		doFilter();
		Assert.assertEquals("[]", ((MockLog) liferayUtil.getLogger()).getMessages());
	}

	private void doFilter() throws IOException, ServletException {
		filter.doFilter(servletRequest, new MockServletResponse(), new MockFilterChain());
	}
}