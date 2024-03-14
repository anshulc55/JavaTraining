package SeleniumScenario1;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class VerifyLinkStatus {

	public static int invalidLinkCount;

	public static void verifyLink(String link) throws IOException {

		if (link != null && !link.isEmpty()) {
			try {
				URL url = new URL(link);

				// Open HTTP Connection
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				// Set Timeout
				connection.setConnectTimeout(3000);

				// Set request method to HEAD to check only the headers, not the entire content
				connection.setRequestMethod("HEAD");

				// Connect the URL
				connection.connect();

				if (connection.getResponseCode() == 200) {

				} else {
					System.out.println(link + " : " + connection.getResponseMessage() + " : " + connection.HTTP_NOT_FOUND);
					invalidLinkCount++;
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void getInvalidLinkCount() {
		System.out.println("Total Invalid Links : " + invalidLinkCount);
	}

}
