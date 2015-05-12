package io.github.codemumbler.liferay.documentlibrary.tracking;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

public class GoogleAnalytics {

	private static final String GOOGLE_ANALYTICS_URL = "http://www.google-analytics.com/collect";
	private static final String TRACKING_PARAMETERS = "v=1&cid=%s&sc=start&dh=%s&t=pageview&dp=%s&dt=%s&tid=%s&uip=%s&ua=%s";

	private String account;
	private String postedParameters;
	private UUID uuid;

	public GoogleAnalytics(final String account, UUID uuid){
		this.account = account;
		this.uuid = uuid;
	}

	public String trackPDF(final String url, final String title, final String remoteAddress, final String userAgent) {
		try {
			URL trackedURL = new URL(url);
			String pdfUrl = trackedURL.getPath();
			String host = trackedURL.getHost();
			URL analyticsSubmissionURL = new URL(GOOGLE_ANALYTICS_URL);

			this.postedParameters = String.format(TRACKING_PARAMETERS, URLEncoder.encode(uuid.toString(), "UTF-8"),
					URLEncoder.encode(host, "UTF-8"), URLEncoder.encode(pdfUrl, "UTF-8"),
					URLEncoder.encode(title, "UTF-8"), URLEncoder.encode(account, "UTF-8"),
					URLEncoder.encode(remoteAddress, "UTF-8"), URLEncoder.encode(userAgent, "UTF-8"));

			HttpURLConnection con = (HttpURLConnection) analyticsSubmissionURL.openConnection();
			postData(con);
			con.getResponseCode();
			return postedParameters;
		} catch (Exception e) {
			throw new RuntimeException("Tracking using Google Analytics failed", e);
		}
	}

	private void postData(final HttpURLConnection con) throws IOException {
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Content-Length",  String.valueOf(postedParameters.length()));
		con.setDoInput(true);
		con.setDoOutput(true);
		DataOutputStream dataOutputStream = new DataOutputStream(con.getOutputStream());
		dataOutputStream.writeBytes(postedParameters);
		try {
			dataOutputStream.flush();
			dataOutputStream.close();
		} catch (IOException e) {

		}
	}

	@Override
	public String toString() {
		return "GoogleAnalytics{" +
				"uuid='" + uuid.toString() + '\'' +
				",account='" + account + '\'' +
				", postedParameters='" + postedParameters + '\'' +
				'}';
	}
}
