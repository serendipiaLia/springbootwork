package com.khit.book.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.khit.book.entity.Book;
import com.khit.book.service.BookService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class BookController {

	private final BookService bookService;
	
	//ResponseEntity<?> : body + header(200, 404)
	// body에 올 수 있는 자료형 - String, Object
	@PostMapping("/book")
	public ResponseEntity<?> save(@RequestBody Book book) { // @RequestBody의 Book은 웹페이지의 입력 폼에서 json형태로 온 데이터
		String resultText = bookService.save(book);
		return new ResponseEntity<>(resultText, HttpStatus.OK);
	}
	
	// 도서 목록 보기
	@GetMapping("/book")
	public ResponseEntity<?> bookList(){
		List<Book> bookList = bookService.findAll();
		return new ResponseEntity<>(bookList, HttpStatus.OK);
	}
	
	// 도서 상세 보기 http://localhost:8080/book
	@GetMapping("/book/{id}")
	public ResponseEntity<?> bookOne(@PathVariable Long id){
		Book book = bookService.findById(id);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	// 도서 삭제 
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> bookDelete(@PathVariable Long id){
		String resultText = bookService.deleteById(id);
		return new ResponseEntity<>(resultText, HttpStatus.OK);
	}
	// 도서 수정
	@PutMapping("/book")
	public ResponseEntity<?> updateBook(@RequestBody Book book) {
		String resultText = bookService.update(book);
		return new ResponseEntity<>(resultText, HttpStatus.OK);
	}
	
	
	
}
