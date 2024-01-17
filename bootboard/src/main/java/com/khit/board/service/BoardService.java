package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;

	// 게시판 글쓰기 처리
	public void save(BoardDTO boardDTO) {
		// BoardDTO를 Entity로 변환
		Board board = Board.toSaveEntity(boardDTO);
		// Entity를 DB에 저장
		boardRepository.save(board);
	}
	// 글 목록 불러오기
	public List<BoardDTO> findAll() {
		// DB에서 Entity list를 가져옴
		List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		// 빈 리스트 생성
		List<BoardDTO> boardDTOList = new ArrayList<>();
		// 가져온 Entity를 DTO로 변환 
		for(Board board : boardList) {
			BoardDTO boardDTO = BoardDTO.toSaveDTO(board);
			boardDTOList.add(boardDTO);
		}
		return boardDTOList;
	}
	// 글 상세보기 
	public BoardDTO findById(Long id) {
		Optional<Board> findBoard = boardRepository.findById(id);
		if(findBoard.isPresent()) { // 찾는 게시글이 있으면, Controller로 보내줌
			BoardDTO boardDTO = BoardDTO.toSaveDTO(findBoard.get());
			return boardDTO;
		}else { // 찾는 게시글이 없으면 에러로 예외처리 
			throw new BootBoardException("게시글을 찾을 수 없습니다.");
		}
	}
	// BoardRepository에 updateHits 메서드 생성
	// 글 조회수!
	@Transactional // Controller에서 메서드(작업)가 2개 이상이면 사용!
	public void updateHits(Long id) { 
		boardRepository.updateHits(id);
	}
	// 글 삭제
	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}
	// 글 수정 처리
	public void update(BoardDTO boardDTO) {
		// save() - 삽입(id가 없고), 수정(id가 있음)
		Board board = Board.toUpdateEntity(boardDTO);
		boardRepository.save(board);
	}
}
