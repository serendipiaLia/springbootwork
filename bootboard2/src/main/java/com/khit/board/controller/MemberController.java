package com.khit.board.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.khit.board.config.SecurityUser;
import com.khit.board.entity.Member;
import com.khit.board.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;



@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService memberService;
	
	// 로그인 페이지
	@GetMapping("/member/login")
	public String loginForm(){
		return "/member/login";
	}
	
//	// 로그인 처리
//	@PostMapping("/member/login")
//	public String login(@ModelAttribute Member member, HttpSession session) {
//		Member loginMember = memberService.login(member);
//		if(loginMember != null && loginMember.getPassword().equals(member.getPassword())) { 
//			// 아이디와 비밀번호가 일치하여 로그인 되면 세션 발급
//			session.setAttribute("sessMemberId", loginMember.getMemberId());
//			return "main";
//		}else {
//			return "/member/login";
//		}
//	}
	// 메인 페이지
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	// 로그아웃
	@GetMapping("/member/logout")
	public String logout() {
		return("/member/logout");
	}
	// 회원 가입
	@GetMapping("/member/join")
	public String joinForm() {
		return "/member/join";
	}
	// 회원 가입 처리
	@PostMapping("/member/join")
	public String join(@ModelAttribute Member member) {
		memberService.save(member);
		return "redirect:/member/login";
	}
	// 회원 목록
	@GetMapping("/member/list")
	public String getList(Model model) {
		List<Member> memberList = memberService.findAll();
		model.addAttribute("memberList" , memberList);
		return "/member/list";
	}
	// 회원 상세보기
	@GetMapping("/member/{id}")
	public String getMember(@PathVariable Integer id, Model model) {
		Member member = memberService.findById(id);
		model.addAttribute("member", member);
		return "/member/detail"; 
	}
	// 회원 삭제
	@GetMapping("/member/delete/{id}")
	public String deleteMember(@PathVariable Integer id) {
		memberService.deleteById(id);
		return "redirect:/member/list";
	}
	//회원 수정
	@GetMapping("/member/update")
	public String updateMember(@AuthenticationPrincipal SecurityUser principal, Model model) {
		 Member member = memberService.findByMemberId(principal);
		 model.addAttribute("member", member);
		 return "/member/update";
	}
	// 회원 정보 수정 처리
	@PostMapping("/member/update")
	public String update(@ModelAttribute Member member) {
		memberService.update(member);
		return "redirect:/member/" + member.getId();
	}
	
	
}
