package com.creativesci.liferay.documentlibrary.tracking;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class MockServletResponse implements ServletResponse {
	public String getCharacterEncoding() {
		return null;
	}

	public String getContentType() {
		return null;
	}

	public ServletOutputStream getOutputStream() throws IOException {
		return null;
	}

	public PrintWriter getWriter() throws IOException {
		return null;
	}

	public void setCharacterEncoding(String s) {

	}

	public void setContentLength(int i) {

	}

	public void setContentType(String s) {

	}

	public void setBufferSize(int i) {

	}

	public int getBufferSize() {
		return 0;
	}

	public void flushBuffer() throws IOException {

	}

	public void resetBuffer() {

	}

	public boolean isCommitted() {
		return false;
	}

	public void reset() {

	}

	public void setLocale(Locale locale) {

	}

	public Locale getLocale() {
		return null;
	}
}
