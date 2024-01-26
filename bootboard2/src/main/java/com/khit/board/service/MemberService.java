package com.khit.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.khit.board.config.SecurityUser;
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
	
	// 로그인 처리
	public Member login(Member member) {
		// db에서 아이디 조회
		Optional <Member> findMember = memberRepository.findByMemberId(member.getMemberId());
		if(findMember.isPresent()) {
			return findMember.get();
		}else {
			return null;
		}
	}
	// 로그인 처리
	public void save(Member member) {
		// 1. 비밀번호 암호화
		// 2. 권한 설정
		String encPw = pwEncoder.encode(member.getPassword());
		member.setPassword(encPw);
		member.setRole(Role.MEMBER);
		
		memberRepository.save(member);
		
	}
	// 회원 목록
	public List<Member> findAll() {
		return memberRepository.findAll();
	}
	// 회원 상세보기
	public Member findById(Integer id) {
		Optional<Member> findMember = memberRepository.findById(id);
		if(findMember.isPresent()) { // 찾는 회원 정보가 있으면
			return findMember.get(); // 정보를 가져와서 반환
		}else {
			throw new BootBoardException("페이지를 찾을 수 없습니다.");
		}
	}
	// 회원 삭제
	public void deleteById(Integer id) {
		memberRepository.deleteById(id);
	}
	// 회원 수정
	public Member findByMemberId(SecurityUser principal) {
		Optional<Member> member = memberRepository.findByMemberId(principal.getUsername()); // memberId == username
		return member.get();
	}
	// 회원 수정 처리
	public void update(Member member) {
		// 암호화 , 권한 설정
		String encPW = pwEncoder.encode(member.getPassword());
		member.setPassword(encPW);
		member.setRole(Role.ADMIN);
		memberRepository.save(member);
	}
	
	
}
