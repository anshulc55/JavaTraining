package com.selenium.masterpart1;

import java.net.HttpURLConnection;
import java.net.URL;

public class GetLinkStatus {

	static int invalidLink;

	public static void verifyLink(String linkString) {

		try {
			URL url = new URL(linkString);
			// Open HTTTP Connection
			HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

			// Define TimeOut
			urlConnect.setConnectTimeout(500);

			// Hit URL
			urlConnect.connect();

			if (urlConnect.getResponseCode() == 200) {

			} else {
				System.out.println(linkString + " : " + urlConnect.getResponseMessage() + " : " + urlConnect.HTTP_NOT_FOUND);
				invalidLink++;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void getInvalidLinkCount() {
		System.out.println("Total Invalid Links : " + invalidLink);
	}

}
