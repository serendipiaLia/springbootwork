package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

//@AllArgsConstructor //new해준 것과 같음
@RequiredArgsConstructor // 생성자 주입 방식 - final을 꼭 붙임
@Service
public class MemberService {

		private final MemberRepository memberRepository;
		
		// 회원가입 처리
		public void save(MemberDTO memberDTO) {
			// db안으로 저장(entity를 저장해야 함)
			// MemberDTO를 entity로 변환하는 메서드 필요
			Member member = Member.toSaveEntity(memberDTO);
			
			memberRepository.save(member);
		}
		// 회원 목록
		public List<MemberDTO> findAll() {
			//DB에서 member Entity를 꺼내옴
			List<Member> memberList = memberRepository.findAll();
			
			// 변환 메서드가 필요! >> MemberDTOList를 담은 빈 DTOList를 생성해주기
			List<MemberDTO> memberDTOList = new ArrayList<>();
			
			for(Member member : memberList) { //memberList를 반복하면서 변환함
				MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
				memberDTOList.add(memberDTO);
			}
			// Controller에 DTOList로 보내기
			return memberDTOList;
		}
		// 회원 정보 상세보기
		public MemberDTO findById(Long id) {
			//db에서 member 1개 꺼내오기 > findById(id).get()으로
//			Member member = memberRepository.findById(id).get(); 
			// id가 없을 때 오류 처리 :  “url을 찾을 수 없습니다.”
			Optional<Member> member = memberRepository.findById(id);
			if(member.isPresent()) {
				// Entity > DTO 변환
				MemberDTO memberDTO = MemberDTO.toSaveDTO(member.get());
				return memberDTO;	
			}else {
				throw new BootBoardException("찾는 데이터가 없습니다."); 
			}
		}
		// 회원 삭제하기
		public void deleteById(Long id) {
			memberRepository.deleteById(id);
		}
		
		// 로그인 처리
		public MemberDTO login(MemberDTO memberDTO) {
			// 1) 이메일로 회원 조회(이메일과 비밀번호 비교)
			Optional<Member>memberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
			if(memberEmail.isPresent()) {
				// 조회 결과가 있을 때.. 1건 가져오기
				Member member = memberEmail.get();
				// 비밀번호 일치
				if(member.getMemberPassword().equals(memberDTO.getMemberPassword())) {
					// Entity를 DTO로 변환
					MemberDTO dto = MemberDTO.toSaveDTO(member);
					return dto;
				}else {
					// 비밀번호 불일치
					return null;
				}
			}else {
				return null;
			}
		}
		
		// 회원정보 수정 페이지
		public MemberDTO findByMemberEmail(String email) {
			// db에서 이메일로 검색한 회원 객체 가져오기
			Member member = memberRepository.findByMemberEmail(email).get();
			// 회원 객체(Entity)를 dto로 변환
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
			return memberDTO;
		}
		// 회원 수정 처리
		public void update(MemberDTO memberDTO) {
			// save가 가입, 수정되는데 가입할 때는 id가 없음 > 수정할 때는 id가 필요함
			Member member = Member.toUpdateEntity(memberDTO); // Member.java
			
			//id가 있는 Entity의 메서드가 필요함
			memberRepository.save(member);
		}
		// 이메일 중복검사
		public String checkEmail(String memberEmail) {
			// db에 있는 이메일 조회해서 있으면 "OK" 아니면 "NO"
			Optional<Member> findMember = memberRepository.findByMemberEmail(memberEmail);
			if(findMember.isEmpty()) { // 이메일 데이터(객체)가 비어있으면 가입OK
				return "OK";
			}else { // 이메일 데이터(객체)가 이미 있으면 가입NO
				return "NO"; 
			}
		}
		
		
}
