package com.khit.study.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.khit.study.entity.Board;
import com.khit.study.repository.BoardRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardService {

	private BoardRepository boardRepository;	
	
	// 글쓰기 처리
	public void save(Board board) {
		boardRepository.save(board);
	}
	
	// 글 목록
	public List<Board> findAll() {
		// 정렬 - 오름차순 (기본)
		// 내림차순으로 바꿔줄 Sort클래스 사용!
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id")); // DESE 내림차순으로 바꿔줌
	}
	// 글 상세보기
	public Board findById(int id) {
		// 1건 검색 - findById().get() 까지 작성
		return boardRepository.findById(id).get();
	}
	// 글 삭제하기
	public void delete(int id) {
		// 1건 삭제 - deleteById(id) >> get필요없음
		boardRepository.deleteById(id);
	}
	// 글 수정하기
	public void update(Board board) {
		// 수정일 직접 생성
		board.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		boardRepository.save(board); // 수정된 후 넘어왔으므로 save사용!
	}

}
