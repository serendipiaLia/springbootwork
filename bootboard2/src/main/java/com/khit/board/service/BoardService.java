package com.khit.board.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.khit.board.entity.Board;
import com.khit.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	
	// 게시글 목록
	public List<Board> findAll() {
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}
	// 게시글 상세보기
	public Board findById(Integer id) {
		return boardRepository.findById(id).get();
	}

}
