package io.github.codemumbler.liferay.documentlibrary;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class MockLiferayUtil extends LiferayUtil {

	private final UUID uuid;
	private boolean isEnabled;
	private Log logger;
	private MockHttpServletRequest httpServletRequest;
	private boolean throwException;

	public MockLiferayUtil(final boolean isEnabled, UUID uuid) {
		this.isEnabled = isEnabled;
		setInstance(this);
		this.logger = new MockLog();
		this.uuid = uuid;
		this.throwException = false;
	}

	public boolean isTrackingEnabled() {
		return this.isEnabled;
	}

	public String getGoogleAnalyticsAccount() {
		return "UA-101101-9";
	}

	public PDFFileEntry createFileEntry(final String[] pathArray) throws Exception {
		if ( throwException )
			throw new NoSuchFileEntryException("File does not exist");
		return new PDFFileEntry(){

			public String getTitle() {
				return "title";
			}
		};
	}

	public HttpServletRequest getOriginalRequest(final ServletRequest servletRequest) {
		return httpServletRequest;
	}

	public void setOriginalRequest(final MockHttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	public Log getLogger() {
		return this.logger;
	}

	@Override
	public UUID getUUID() {
		return this.uuid;
	}

	public void setThrowFileEntryException(boolean throwException) {
		this.throwException = throwException;
	}
}
