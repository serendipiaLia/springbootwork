package com.khit.board.jsondata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiJson {

	public static void main(String[] args) {
		
		try {
			// 지진 해일 대피소 데이터 테스트
			String serviceKey = "3e6KfjqGd%2BSHh5QtxpCL6hAh8NyMv%2Bzbbz4btxYu3dmUvLkrW6msC23slFSCHRrGmyHzajC8cTa1OcvCICFebQ%3D%3D";
			String url ="https://apis.data.go.kr/1741000/TsunamiShelter3/getTsunamiShelter1List?serviceKey=" + serviceKey + "&pageNo=1&numOfRows=10&type=json";
			
			// 데이터를 받기 위해서 URL 클래스의 객체 생성
			URL requestUrl = new URL(url);
			System.out.println(url);
			
			// openConnection()을 이용한 연결
			HttpURLConnection conn = (HttpURLConnection)requestUrl.openConnection();
			conn.setRequestMethod("GET"); // 대문자로 명시
			
			// 응답 상세 코드(200, 403, 404, 500)
			int status = conn.getResponseCode();
			System.out.println(status);
			
			// 버퍼 : 입출력 속도 향상을 위해서 데이터를 일시적으로 메모리 영역에 모아놓은 곳
			// BufferedReader(보조스트림) : 기반 스트림(쌩성자) - InputStreamReader
			InputStreamReader isr = new InputStreamReader(conn.getInputStream()); 
			BufferedReader br = new BufferedReader(isr);
			
			String responseText = "";
			String line; // 한 행에 있는 데이터
			while((line = br.readLine()) != null ) {
				responseText += line;
			}
			System.out.println(responseText);
			
			br.close(); //BufferedReader 종료
			conn.disconnect(); // HttpURLConnection 종료
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
