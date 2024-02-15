package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	
	// 게시글 목록
	public List<BoardDTO> findAll() {
		// DB에서 boardList 가져오기
		List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		
		List<BoardDTO> boardDTOList = new ArrayList<>();
		
		for(Board board : boardList) {
			BoardDTO boardDTO = BoardDTO.toSaveDTO(board);
			boardDTOList.add(boardDTO);
		}
		return boardDTOList;
	}
	// 게시글 상세보기
	public Board findById(Integer id) {
		return boardRepository.findById(id).get();
	}
	// 글쓰기 처리
	public void save(Board board) {
		boardRepository.save(board);
	}
	// 글 삭제
	public void deleteById(Integer id) {
		boardRepository.deleteById(id);
	}
	// 글 수정 처리
	public void update(Board board) {
		boardRepository.save(board);
	}
	
	
}
