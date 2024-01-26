package com.khit.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khit.board.entity.Users;

@RestController  // 데이터 전달이 역할인 어노테이션임
public class SampleController {
	
//	// 연습
//	@GetMapping("/sample")
//	public String test() {
//		return "안녕하세요.";
//	}

	// REST 방식으로 CRUD 진행
	// GET - SELECT(Research)
	// 객체를 반환하면 json형태로 전달됨!
	@GetMapping("/khit")
	public Users httpGet() {
		// user 1명 생성 후, 데이터 검색(보기)
		Users user = Users.builder()
				.id(1)
				.username("today")
				.password("1234")
				.email("today@kh.kr")
				.build();
		return user;
	}
	// POST - INSERT(Create)
	// Junit처럼 메인없이 테스트하고 싶을 때 -> Postman 이용
	// 전달받은 데이터가 json형태 : {key : value}일때 RequestBody로 받음
	@PostMapping("/khit")
	public String httpPost(@RequestBody Users users) { //form형태
		return "POST 요청 처리" + users.toString();
	}
	
	// PUT - UPDATE(update)
	@PutMapping("/khit")
	public String httpPut(@RequestBody Users users) {
		return "PUT 요청 처리" + users.toString();
	}
	
	// DELETE - DELETE(Delete)
	// @PathVariable : 경로의 변수{id}를 전달 받음
	@DeleteMapping("/khit/{id}")
	public String httpDelete(@PathVariable Integer id) {
		return "DELETE 요청 처리 " + id;
	}
	
}
