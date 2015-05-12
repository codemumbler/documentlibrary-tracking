package io.github.codemumbler.liferay.documentlibrary;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MockHttpServletRequest implements HttpServletRequest {

	private String requestURL;
	private String ip;
	private Map<String, String> headers = new HashMap<String, String>();

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public void setRemoteAddr(String ip) {
		this.ip = ip;
	}

	public String getAuthType() {
		return null;
	}

	public Cookie[] getCookies() {
		return new Cookie[0];
	}

	public long getDateHeader(String s) {
		return 0;
	}

	public String getHeader(String s) {
		return headers.get(s.toLowerCase());
	}

	public Enumeration getHeaders(String s) {
		return null;
	}

	public Enumeration getHeaderNames() {
		return null;
	}

	public int getIntHeader(String s) {
		return 0;
	}

	public String getMethod() {
		return null;
	}

	public String getPathInfo() {
		return null;
	}

	public String getPathTranslated() {
		return null;
	}

	public String getContextPath() {
		return null;
	}

	public String getQueryString() {
		return null;
	}

	public String getRemoteUser() {
		return null;
	}

	public boolean isUserInRole(String s) {
		return false;
	}

	public Principal getUserPrincipal() {
		return null;
	}

	public String getRequestedSessionId() {
		return null;
	}

	public String getRequestURI() {
		return null;
	}

	public StringBuffer getRequestURL() {
		return new StringBuffer(this.requestURL);
	}

	public String getServletPath() {
		return null;
	}

	public HttpSession getSession(boolean b) {
		return null;
	}

	public HttpSession getSession() {
		return null;
	}

	public boolean isRequestedSessionIdValid() {
		return false;
	}

	public boolean isRequestedSessionIdFromCookie() {
		return false;
	}

	public boolean isRequestedSessionIdFromURL() {
		return false;
	}

	public boolean isRequestedSessionIdFromUrl() {
		return false;
	}

	public Object getAttribute(String s) {
		return null;
	}

	public Enumeration getAttributeNames() {
		return null;
	}

	public String getCharacterEncoding() {
		return null;
	}

	public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

	}

	public int getContentLength() {
		return 0;
	}

	public String getContentType() {
		return null;
	}

	public ServletInputStream getInputStream() throws IOException {
		return null;
	}

	public String getParameter(String s) {
		return null;
	}

	public Enumeration getParameterNames() {
		return null;
	}

	public String[] getParameterValues(String s) {
		return new String[0];
	}

	public Map getParameterMap() {
		return null;
	}

	public String getProtocol() {
		return null;
	}

	public String getScheme() {
		return null;
	}

	public String getServerName() {
		return null;
	}

	public int getServerPort() {
		return 0;
	}

	public BufferedReader getReader() throws IOException {
		return null;
	}

	public String getRemoteAddr() {
		return this.ip;
	}

	public String getRemoteHost() {
		return null;
	}

	public void setAttribute(String s, Object o) {

	}

	public void removeAttribute(String s) {

	}

	public Locale getLocale() {
		return null;
	}

	public Enumeration getLocales() {
		return null;
	}

	public boolean isSecure() {
		return false;
	}

	public RequestDispatcher getRequestDispatcher(String s) {
		return null;
	}

	public String getRealPath(String s) {
		return null;
	}

	public int getRemotePort() {
		return 0;
	}

	public String getLocalName() {
		return null;
	}

	public String getLocalAddr() {
		return null;
	}

	public int getLocalPort() {
		return 0;
	}
}
