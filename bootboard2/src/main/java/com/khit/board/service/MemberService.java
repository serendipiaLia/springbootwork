package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.khit.board.config.SecurityUser;
import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.entity.Role;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	private final PasswordEncoder pwEncoder;

	public Member login(Member member) {
		// DB에서 아이디 조회
		Optional<Member> findMember = memberRepository.findByMemberId(member.getMemberId());
		if(findMember.isPresent()) {
			return findMember.get();
		} else {
			return null;
		}
	}
	// 로그인
	public void save(MemberDTO memberDTO) {
		// 1. 비밀번호 암호화
		// 2. 권한 설정
		String encPw = pwEncoder.encode(memberDTO.getPassword());
		memberDTO.setRole(Role.MEMBER);
		memberDTO.setPassword(encPw);
		
		// 변환 메서드 필요 -> 최종적으로 member로 들어가야 한다.
		Member member = Member.toSaveEntity(memberDTO);
		
		memberRepository.save(member);
	}
	// 회원 목록
	public List<MemberDTO> findAll() {
		// DB에서 memberList를 가져와야 함
		List<Member> memberList = memberRepository.findAll();
		
		// 빈 memberDTOList를 생성
		List<MemberDTO> memberDTOList = new ArrayList<>();
		// memberDTOList에 memberDTO를 채움
		for(Member member : memberList) {
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
			memberDTOList.add(memberDTO);
		}
		
		return memberDTOList;
	}
	public MemberDTO findById(Integer id) {
		// DB에서 member를 꺼내옴
		Optional<Member> findMember = memberRepository.findById(id);
		if(findMember.isPresent()) { // 회원 정보가 있으면
			// 변환
			MemberDTO memberDTO = MemberDTO.toSaveDTO(findMember.get());
			return memberDTO;
		} else {
			throw new BootBoardException("페이지를 찾을 수 없습니다.");
		}
	}
	// 회원 삭제
	public void deleteById(Integer id) {
		memberRepository.deleteById(id);
	}
	//회원정보 수정
	public MemberDTO findByMemberId(SecurityUser principal) {
		Optional<Member> member = memberRepository.findByMemberId(principal.getUsername());
		//변환
		MemberDTO memberDTO = MemberDTO.toSaveDTO(member.get());
		return memberDTO;
	}
	// 회원정보 수정 처리
	public void update(MemberDTO memberDTO) {
		String encPW = pwEncoder.encode(memberDTO.getPassword());
		memberDTO.setPassword(encPW);
		memberDTO.setRole(Role.MEMBER);
		
		// 변환
		Member member = Member.toSaveUpdate(memberDTO);
		memberRepository.save(member);
	}

	public String findByMemberId(String memberId) {
		//db에 있는 아이디 조회해서 있으면 "OK" 아니면 "NO"를 보냄
		Optional<Member> findMember = memberRepository.findByMemberId(memberId);
		if(findMember.isPresent()) {
			return "NO";
		}else {
			return "OK";
		}
	}

}
