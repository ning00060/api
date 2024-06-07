package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TempHttp {
	int userId;
	int id;
	String title;
	String completed;

	public static void main(String[] args) throws IOException {
		String stringUrl = "https://jsonplaceholder.typicode.com/todos";

		URL url = new URL(stringUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		System.out.println("openconnection" + conn);

		conn.setRequestMethod("GET");

		int responsecode = conn.getResponseCode();
		System.out.println("http 코드" + responsecode);

		BufferedReader brIn = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String inputLine;
		StringBuffer responseBuffer = new StringBuffer();

		while ((inputLine = brIn.readLine()) != null) {
			responseBuffer.append(inputLine);

		}
		System.out.println(responseBuffer);
		brIn.close();
		String[] strHtmls = responseBuffer.toString().split("\\s");
		for (String word : strHtmls) {
			System.out.println(word);
		}
		String[] strHtmls2 = responseBuffer.toString().split("[\\{,\\}]");
		for (String word2 : strHtmls2) {
			System.out.println(word2);
			
		}

	}
}
