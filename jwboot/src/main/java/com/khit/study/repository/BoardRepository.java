package com.khit.study.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.study.entity.Board;

//JPARepository를 상속해야 함	
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	//쿼리 메서드 실습
	// 제목으로 검색
	// select * from board where title = ?
	List<Board> findByTitle(String searchKeyword); // 구현부가 없는 추상메서드
	
	// 제목이 포함된 키워드 검색
	// select * from board where title like = ?
	List<Board> findByTitleContaining(String searchKeyword);
	
	// 제목 or 내용
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	
/* ----------------------------------------------------
	 	검색한 내용 결과 정렬하기! (내림차순, 오름차순)
-------------------------------------------------------*/
	// 제목에 특정 단어가 포함된 글 목록을 내림차순으로 조회 
	List<Board> findByTitleContainingOrderByIdDesc(String keyword);
	
	// 제목에 특정 단어가 포함된 글 목록을 페이지 처리하여 조회
	List<Board> findByTitleContaining(String keyword, Pageable pageable); //Pageable은 import org.springframework.data.domain.Pageable; 로 Import해주기
	
}
