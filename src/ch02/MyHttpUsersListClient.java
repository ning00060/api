package ch02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MyHttpUsersListClient {

	public static void main(String[] args) {

		// 순수 자바 코드에서 HTTP 통신
		// 1.서버 주소 경로
		// 2. URL 클래스
		// 3. url.openConnection() <--- 스트림 I/O

		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/users");
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
//			User userDTO = gson.fromJson(buffer.toString(), User.class);
			Type userType = new TypeToken<List<User>>() {
			}.getType();

			List<User> userList = gson.fromJson(buffer.toString(), userType);

			System.out.println(userList.size());
			for (User user : userList) {
				System.out.println(user.toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}// main
}
