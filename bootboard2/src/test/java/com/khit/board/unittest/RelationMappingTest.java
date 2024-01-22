package com.khit.board.unittest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.board.repository.BoardRepository;
import com.khit.board.repository.MemberRepository;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Data
@ToString
@Slf4j
@SpringBootTest
public class RelationMappingTest {
	//sql 제공 - MemberRepository : save(), findById()
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	/*=================================================================================
	 *  데이터 생성
	 ================================================================================= */
	// USER 유저 or ADMIN 관리자 
//	@Test
//	public void testInsertData() {
//		Member member1 = new Member(); // 멤버를 담아줄 박스
//		// id는 자동생성이므로 입력 x
//		member1.setMemberId("member1");
//		member1.setPassword("123456");
//		member1.setName("테스터1");
//		member1.setRole("USER");
//		
//		memberRepository.save(member1); // memberRepository에 저장해줄 member1
//	
//		// 관리자
//		// id는 자동생성이므로 입력 x
//		Member member2 = new Member();
//		member2.setMemberId("member2");
//		member2.setPassword("12345678");
//		member2.setName("테스터2");
//		member2.setRole("ADMIN");
//		
//		memberRepository.save(member2); // memberRepository에 저장해줄 member1
//		
//		// 회원이 등록한 글
//		for(int i=1; i<=3; i++) {
//			Board board = new Board();
//			board.setTitle(" 테스터1이 입력한 글 제목 : " +  i);
//			board.setContent("테스터이 입력한 글 내용" + i);
//			board.setMember(member1);
//			board.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//			
//			boardRepository.save(board);
//		}
//		// 관리자가 등록한 글
//		for(int i=1; i<=3; i++) {
//			Board board = new Board();
//			board.setTitle(" 테스터2가 입력한 글 제목 : " +  i);
//			board.setContent("테스터2가 입력한 글 내용" + i);
//			board.setMember(member2);
//			board.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//			boardRepository.save(board);
//		}
//	}
	/*=================================================================================
	 *  데이터 조회
	 ================================================================================= */
	// 전체 조회(회원목록, 글 목록)
//	@Test
//	public void testSelectAll() {
//		//회원목록
//		List<Member> memberList = memberRepository.findAll();
//		
//		for(Member member : memberList) {
//			log.info(member.toString());
//		}
//		// 게시글 목록
//		List<Board> boardList = boardRepository.findAll();
//		
//		boardList.forEach(board -> log.info(board.toString()));
//	}
//	
//	// 게시글 상세 조회
//	@Test
//	public void testSelectOne() {
//		// 4번 게시글 조회 - findById(4)
//		Board board = boardRepository.findById(4).get();
//		log.info(board.getId() + " 번 게시글 정보");
//		log.info("제목 " + board.getTitle());
//		log.info("내용 :" + board.getContent());
//		log.info("작성자 : " + board.getMember().getName());
//		log.info("작성자 권한 : " + board.getMember().getRole());
//	}
//	// 특정 회원이 게시한 글 조회
//	@Test
//	public void testSelect() {
//		//테스터 1이 쓴 게시글 조회
//		Member member = memberRepository.findByMemberId("member1");
//		
//		log.info(member.getName() + "이(가) 작성한 게시글 목록");
//		
//		List<Board> boardList = member.getBoardList();
//		for(Board board : boardList)
//			log.info(board.toString());
//	}
	/*=================================================================================
	 *  데이터 삭제
	 ================================================================================= */

	
}
