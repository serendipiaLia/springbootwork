package com.khit.study.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.study.entity.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;
	
	// 게시글 생성
	@Test
	public void insertBoard() {
//		Board board = new Board();
//		board.setTitle("다들 안뇽");
//		board.setWriter("L");
//		board.setContent("hello~");
//		board.setCreatedDate(new Timestamp(System.currentTimeMillis())); 
		
//		// Board.java에서 @Builder 사용 후 위 메소드 주석
	
//		Board board = Board.builder()
//				.title("점심메뉴")
//				.writer("Lia")
//				.content("뭐먹찌")
//				.createdDate(new Timestamp(System.currentTimeMillis()))
//				.build();
//		
//		// db에 저장
//		boardRepository.save(board);
	}	
		// MySQL에서 select해본 후, 추가한 데이터가 있으면 다음 메소드 실행
		
		@Test
		public void getBoardList() {
			// db의 게시글 목록 가져오기
			List<Board> boardList = boardRepository.findAll();
			
//			// boardList출력
//			for(Board board : boardList)
//				log.info(board.toString());
			
			// 위의 출력메서드 람다식으로 표현
			boardList.forEach(board -> log.info(board.toString()));
	}
		// 1건상세보기
		@Test
		public void getBoard() {
			//findById()와 get() 사용
			Board board = boardRepository.findById(1).get();
			log.info(board.toString());
		}
		
		// 수정하기
		@Test
		public void updateBoard() {
			// 수정하려는 게시글을 가져와서(findById) 수정 처리(save)
			Board board = boardRepository.findById(1).get();
			board.setTitle("다들 안뇽하십니까");
			board.setContent("히히");
			
			boardRepository.save(board);
		}
		
		// 삭제하기
		@Test
		public void deleteBoard() {
			// (2)번 게시글 삭제
			boardRepository.deleteById(2);
		}
}