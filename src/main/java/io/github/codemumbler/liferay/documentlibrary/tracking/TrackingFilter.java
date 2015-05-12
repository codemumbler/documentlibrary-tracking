package io.github.codemumbler.liferay.documentlibrary.tracking;

import io.github.codemumbler.liferay.documentlibrary.LiferayUtil;
import io.github.codemumbler.liferay.documentlibrary.PDFFileEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.util.*;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TrackingFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		LiferayUtil liferay = LiferayUtil.getInstance();
		Log log = liferay.getLogger();
		if (!liferay.isTrackingEnabled()) {
			log.trace("Document Library tracking currently disabled");
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		try {
			HttpServletRequest httpRequest = liferay.getOriginalRequest(servletRequest);
			String uri = (String) servletRequest.getAttribute(WebKeys.INVOKER_FILTER_URI);
			uri = StringUtil.replaceFirst(uri, "/documents/", StringPool.BLANK);
			if ( uri.toLowerCase().contains(".pdf") || uri.toLowerCase().contains(".doc") ) {
				String[] pathArray = StringUtil.split(uri, CharPool.SLASH);
				if (Validator.isNumber(pathArray[0])) {
					PDFFileEntry fileEntry = liferay.createFileEntry(pathArray);
					GoogleAnalytics ga = new GoogleAnalytics(liferay.getGoogleAnalyticsAccount(), liferay.getUUID());
					String clientIP = getClientIp(httpRequest);
					log.debug("Requested URL: " + httpRequest.getRequestURL().toString());
					log.debug("Requesting User-Agent: " + httpRequest.getHeader("User-Agent"));
					log.debug("Requesting IP: " + clientIP);
					ga.trackPDF(httpRequest.getRequestURL().toString(), fileEntry.getTitle(), clientIP,
							httpRequest.getHeader("User-Agent"));
					log.debug("Document Library tracking info: " + ga.toString());
				}
			}
		} catch (NoSuchFileEntryException e) {
			log.trace("The document failed to track because it doesn't exist.");
		} catch (Exception e) {
			log.error("Failed to track document library item", e);
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	private String getClientIp(final HttpServletRequest httpRequest) {
		if ( httpRequest.getHeader("X-Forwarded-For") != null ) {
			return httpRequest.getHeader("X-Forwarded-For").split(",")[0].trim();
		}
		return httpRequest.getRemoteAddr();
	}

	public void destroy() {

	}
}
