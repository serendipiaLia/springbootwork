package com.khit.test.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(value="air.do")
public class APIController {

// 메서드 명 getShelterList
// ajax 요청 성공 : table 형식으로 응답 데이터 출력
// ajax 요청 실패 : "ajax 통신 실패!" 문구를 console에 출력 
	public static final String serviceKey = "3e6KfjqGd%2BSHh5QtxpCL6hAh8NyMv%2Bzbbz4btxYu3dmUvLkrW6msC23slFSCHRrGmyHzajC8cTa1OcvCICFebQ%3D%3D";
	
	@GetMapping("/disaster.do")
	public @ResponseBody String airPollution(String location) throws UnsupportedEncodingException {
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
			url += "?serviceKey=" + serviceKey;
			url += "&sidoName=" + URLEncoder.encode(location, "UTF-8");
			url += "&returnType=xml";
			url += "&numOfRows=20";

		try {
			// 데이터를 받기 위해서 URL 클래스의 객체 생성
			URL requestUrl = new URL(url);
			
			// openConnection()을 이용한 연결
			HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
			urlConnection.setRequestMethod("GET"); 
			
			InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream()); //input
			BufferedReader br = new BufferedReader(isr);
			
			String responseText = "";
			String line; 
			while((line = br.readLine()) != null) {
				responseText += line;
				}
				br.close();
				urlConnection.disconnect();

			return responseText;
		} catch (IOException e) {
			e.printStackTrace();
		} //url 클랙스 객체 생성
		return null; // return 작성
	}
		
	
}
