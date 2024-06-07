package ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyHttpUsersClient {

	public static void main(String[] args) {

		// 순수 자바 코드에서 HTTP 통신
		// 1.서버 주소 경로
		// 2. URL 클래스
		// 3. url.openConnection() <--- 스트림 I/O

		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/users/1");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			// 응답 코드 확인
			int responseCode = conn.getResponseCode();
			System.out.println("response code :" + responseCode);

			// HTTP 응답 메세지에 데이터를 추출 [] --- Stream --- []
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer buffer = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				buffer.append(inputLine);
			}
			in.close();

			System.out.println(buffer.toString());
			System.err.println("--------------------");
			// gson lib 활용
//			Gson gson =new Gson();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			User userDTO = gson.fromJson(buffer.toString(), User.class);

			System.out.println(userDTO.getId());
			System.out.println(userDTO.getName());
			System.out.println(userDTO.getUsername());
			System.out.println(userDTO.getEmail());
			System.out.println(userDTO.getAddress().street);
			System.out.println(userDTO.getAddress().suite);
			System.out.println(userDTO.getAddress().city);
			System.out.println(userDTO.getAddress().zipcode);
			System.out.println(userDTO.getAddress().geo.lat);
			System.out.println(userDTO.getAddress().geo.lng);
			System.out.println(userDTO.getPhone());
			System.out.println(userDTO.getWebsite());
			System.out.println(userDTO.getCompany());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// main
}
