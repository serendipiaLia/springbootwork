package com.khit.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 사용자 Exception은 RuntimeException을 상속받아야 함 
// @ResponseStatus : 404 오류표시하는 클래스 (상태코드)
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BootBoardException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	// 생성자
	public BootBoardException(String message) {
		super(message);
	}
	
	
}
