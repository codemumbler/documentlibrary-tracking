package com.creativesci.liferay.documentlibrary;

import com.liferay.portal.kernel.log.Log;

import java.util.ArrayList;
import java.util.List;

public class MockLog implements Log {

	private List<String> messages = new ArrayList<String>();

	public void debug(Object o) {
		messages.add(o.toString());
	}

	public void debug(Object o, Throwable throwable) {
		messages.add(o.toString() + " :: " + throwable.getMessage());
	}

	public void debug(Throwable throwable) {

	}

	public void error(Object o) {
		messages.add(o.toString());
	}

	public void error(Object o, Throwable throwable) {
		messages.add(o.toString() + " :: " + throwable.getMessage());
	}

	public void error(Throwable throwable) {

	}

	public void fatal(Object o) {
		messages.add(o.toString());
	}

	public void fatal(Object o, Throwable throwable) {
		messages.add(o.toString() + " :: " + throwable.getMessage());
	}

	public void fatal(Throwable throwable) {

	}

	public void info(Object o) {
		messages.add(o.toString());
	}

	public void info(Object o, Throwable throwable) {
		messages.add(o.toString() + " :: " + throwable.getMessage());
	}

	public void info(Throwable throwable) {

	}

	public boolean isDebugEnabled() {
		return false;
	}

	public boolean isErrorEnabled() {
		return false;
	}

	public boolean isFatalEnabled() {
		return false;
	}

	public boolean isInfoEnabled() {
		return false;
	}

	public boolean isTraceEnabled() {
		return false;
	}

	public boolean isWarnEnabled() {
		return false;
	}

	@Override
	public void setLogWrapperClassName(String s) {

	}

	public void trace(Object o) {
		messages.add(o.toString());
	}

	public void trace(Object o, Throwable throwable) {
		messages.add(o.toString() + " :: " + throwable.getMessage());
	}

	public void trace(Throwable throwable) {

	}

	public void warn(Object o) {
		messages.add(o.toString());
	}

	public void warn(Object o, Throwable throwable) {
		messages.add(o.toString() + " :: " + throwable.getMessage());
	}

	public void warn(Throwable throwable) {

	}

	public String getMessages() {
		return messages.toString();
	}
}
