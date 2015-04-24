package com.creativesci.liferay.documentlibrary.tracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class GoogleAnalyticsTest {

	//TODO eventually use the GA realtime API to ensure that the unique pdf and title exist

	@Test
	public void trackPDF() {
		UUID uuid = UUID.randomUUID();
		GoogleAnalytics ga = new GoogleAnalytics("UA-101011-9", uuid);
		String tempId = UUID.randomUUID().toString();
		final String expectedUrlParams = "v=1&cid=" + uuid.toString() + "&sc=start&dh=example.com&t=pageview&dp=%2Fdocuments%2Fpdf%2Fpdf" + tempId +
				".pdf&dt=title+-+" + tempId + "&tid=UA-101011-9&uip=1.1.1.1&ua=JUnit+test";
		String urlParamResults = ga.trackPDF("http://example.com/documents/pdf/pdf" + tempId + ".pdf", "title - " + tempId, "1.1.1.1", "JUnit test");
		Assert.assertEquals(expectedUrlParams, urlParamResults);
	}
}