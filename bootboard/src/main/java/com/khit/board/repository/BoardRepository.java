package com.khit.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.khit.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	// JPA 기본 제공 메서드 - save(), findAll(), findById(), deleteById()
	
	@Modifying // 수정(변경)이 생겼을 때 사용하는 어노테이션
	@Query(value="update Board b set b.boardHits = b.boardHits+1 where b.id = :id")
	public void updateHits(Long id);

	/* 글 목록 검색 처리! >> 검색어가 없으면 페이지 처리하고, 검색어가 있으면 검색어로 페이지 처리 */
	// Title이므로 제목으로 검색하고 페이지 처리됨
	public Page<Board> findByBoardTitleContaining(String keyword, Pageable pageable);
	// Content이므로 내용으로 검색하고 페이지 처리됨
	public Page<Board> findByBoardContentContaining(String keyword, Pageable pageable); 
}
