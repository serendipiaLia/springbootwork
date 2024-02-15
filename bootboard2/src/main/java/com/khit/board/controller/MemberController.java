package com.khit.board.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.khit.board.config.SecurityUser;
import com.khit.board.dto.MemberDTO;
import com.khit.board.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	// 로그인 페이지 요청
	@GetMapping("/member/login")
	public String getLogin() {
		return "/member/login";
	}
	
	// 로그인 처리 - 세션 사용 시
	/* @PostMapping("/member/login")
	public String login(@ModelAttribute Member member, HttpSession session) {
		Member loginMember = memberService.login(member);
		if(loginMember != null && loginMember.getPassword().equals(member.getPassword())) {
			// 아이디와 비밀번호가 일치해 로그인을 성공하면 세션 발급
			session.setAttribute("sessMemberId", loginMember.getMemberId());
			return "main";
		} else {
			return "/member/login";
		}
	} */
	
	// 메인 페이지
	@GetMapping("/main")
	public String main() {
		return "main";
	}

	// 로그아웃
	@GetMapping("/member/logout")
	public String logout() {
		return "redirect:/";
	}
	
	// 회원가입
	@GetMapping("/member/join")
	public String joinForm(MemberDTO memberDTO) {
		return "/member/join";
	}
	
	// 회원가입 처리
	// @Valid: 필드의 유효성 검사
	@PostMapping("/member/join")
	public String join(@Valid MemberDTO memberDTO, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return"/member/join"; //에러가 있으면 회원가입 페이지에 머무름
		}
		
		memberService.save(memberDTO);
		return "redirect:/member/login";
	}
	
	// 회원목록
	@GetMapping("/member/list")
	public String getMemberList(Model model) {
		List<MemberDTO> memberDTOList = memberService.findAll();
		model.addAttribute("memberList", memberDTOList);
		return "/member/list";
	}
	
	// 회원 상세보기
	@GetMapping("/member/{id}")
	public String getMemberDetail(@PathVariable Integer id, Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO);
		return "/member/detail";
	}
	
	// 회원 삭제
	@GetMapping("/member/delete/{id}")
	public String getMemberDelete(@PathVariable Integer id) {
		memberService.deleteById(id);
		return "redirect:/member/list";
	}
	
	//회원 수정 페이지
	@GetMapping("/member/update")
	public String updateMember(@AuthenticationPrincipal SecurityUser principal, Model model) {
		MemberDTO memberDTO = memberService.findByMemberId(principal);
		model.addAttribute("member", memberDTO);
		return "/member/update";
	}
	
	//회원 수정 처리
	@PostMapping("/member/update")
	public String update(@ModelAttribute MemberDTO memberDTO) {
		memberService.update(memberDTO);
		return "redirect:/member/" + memberDTO.getId();
	}
	
	
}
