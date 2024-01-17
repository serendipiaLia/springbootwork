package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khit.board.dto.BoardDTO;
import com.khit.board.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
@Controller	
public class BoardController {
	
	private final BoardService boardService;
	
	// 글쓰기 페이지
	@GetMapping("/write")
	public String writeForm(BoardDTO boardDTO) {
		return "/board/write";
	}
	// 글쓰기 처리
	@PostMapping("/write")
	public String write(@Valid BoardDTO boardDTO, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) { //에러가 있으면 글쓰기 폼으로 이동
			log.info("has errors.......");
			return "/board/write";
		}
		// 글 저장
		boardService.save(boardDTO);
		return "redirect:/board/list";
	}
	// 글 목록
	@GetMapping("/list")
	public String getList(Model model) {
		List<BoardDTO> boardDTOList = boardService.findAll(); // 화면에 불러와져야하는 것 BoardDTO
		model.addAttribute("boardList", boardDTOList); // boardDTOList를 boardList라는 변수에 담음
		return "/board/list";
	}
	// 글 상세보기 
	@GetMapping("/{id}")
	public String getBoard(@PathVariable Long id, Model model) {
		
		// 조회수
		boardService.updateHits(id);
		// 글 상세보기
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		return "/board/boardDetail";
	}
	// 글 삭제
	@GetMapping("/delete/{id}")
	public String deleteBoard(@PathVariable Long id) {
		boardService.deleteById(id);
		return "redirect:/board/list";
	}
	
	// 글 수정 페이지
	@GetMapping("/boardUpdate/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		// 수정할 해당 페이지 가져오기
		
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		return "/board/boardUpdate";
	}
	// 글 수정 처리
	@PostMapping("/update")
	public String update(@ModelAttribute BoardDTO boardDTO){
		// 수정 후에 글 상세보기로 이동
		boardService.update(boardDTO);
		return "redirect:/board/" + boardDTO.getId();
	}
	
}
