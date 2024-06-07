package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ApiTest {

	public static void main(String[] args) throws IOException {

		// 순수 자바코드로 (클라이언트측 코딩)
		// 준비물
		// 1.서버측 주소 -경로
		// http://localhost:8080/test?name=홍길동&age=20
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=NuvfXP%2F6V%2FHc5PScgzM47A9vTFOBD31OSjtQ1aEuuQkRKCtE2dM1bntOKgL%2BXeSNNNLTcE5gN31z5whViH4PSA%3D%3D"); /*
																															 * Service
																															 * *
																															 * Key
																															 */
		urlBuilder.append(
				"&" + URLEncoder.encode("desc_kor", "UTF-8") + "=" + URLEncoder.encode("바나나칩", "UTF-8")); /* 식품이름 */
		urlBuilder
				.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
		urlBuilder.append(
				"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /* 한 페이지 결과 수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("bgn_year", "UTF-8") + "=" + URLEncoder.encode("2017", "UTF-8")); /* 구축년도 */
		urlBuilder.append("&" + URLEncoder.encode("animal_plant", "UTF-8") + "="
				+ URLEncoder.encode("(유)돌코리아", "UTF-8")); /* 가공업체 */
		urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "="
				+ URLEncoder.encode("xml", "UTF-8")); /* 응답데이터 형식(xml/json) Default: xml */

		// URL 객체에서 문자열 경로 넣어서 객체 생성
		// url.openConnection() 데이터 요청 보내기- 설정하고

		URL url = new URL(urlBuilder.toString());

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");// 서버에게 자원 요청
		conn.setRequestProperty("Content-type", "application/json");
		// 200, 실패 404, 405
		System.out.println("Response code: " + conn.getResponseCode());

		// 100~500 의미( 약속 )
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());
	}// main

}
