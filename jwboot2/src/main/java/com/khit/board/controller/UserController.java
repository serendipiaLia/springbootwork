package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khit.board.entity.User;
import com.khit.board.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller // @RestController >>  @Controller + @ResponseBody + @RequestBody
public class UserController {

	private final UserService userService;
	
	// 회원가입 > POST
	// PostMan에서 보낸 json형태의 자료를 입력받아 DB에 저장!
	@PostMapping("/user")
	public @ResponseBody String insertUser(@RequestBody User user) {
		userService.save(user);
		return "회원가입 성공!";
	}
	// 회원 목록 보기 > GET
	@GetMapping("/user/list")
	public @ResponseBody List<User> getList(){
		List<User> userList = userService.findAll();
		return userList;
	}
	// 회원 상세보기
	// id로 검색 > GET
	@GetMapping("/user/{id}")
	public @ResponseBody User getUser(@PathVariable Integer id){
		User findUser = userService.findById(id);
		return findUser;
	}
	// 회원 수정
	@PutMapping("/user")
	public @ResponseBody String updateUser(@RequestBody User user) {
		userService.update(user);
		return "회원 수정 성공 !";
	}
	// 회원 삭제
	@DeleteMapping("/user/{id}")
	public @ResponseBody String deleteUser(@PathVariable Integer id) {
		userService.deleteById(id);
		return "회원 삭제! ";
	}
}
