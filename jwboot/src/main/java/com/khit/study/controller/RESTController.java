package com.khit.study.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khit.study.entity.BoardVO;
import com.khit.study.service.BoardRESTService;

import lombok.AllArgsConstructor;

// @RestController : 문자열을 반환하는 어노테이션 (웹에 return값을 문자열로 출력)
// @ResponseBody, @ResponseEntity와 비슷함

@AllArgsConstructor
@RestController // 객체를 문자열로 변환하는 클래스
public class RESTController {

	private BoardRESTService boardService;
	
	@GetMapping("/greeting")
	public String sayHello(String name) {
		return "hello~" + name + " :D"; // 문자열! hello.html 실제파일아님
	}
	
	@GetMapping("/board/detail")
	public BoardVO getBoard() {
		BoardVO board = boardService.getBoard();
		return board;
	}
	
	@GetMapping("/board/list")
	public List<BoardVO> getBoardList(){
		List<BoardVO> boardList = boardService.getBoardList();
		return boardList;
		}
}
	
