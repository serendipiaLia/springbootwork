package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khit.board.dto.MemberDTO;
import com.khit.board.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberContorller {
	
	private final MemberService memberService;
	
	// 회원가입 페이지
	@GetMapping("/member/join")
	public String joinForm() {
		return "/member/join";
	}
	// 회원가입 처리
	@PostMapping("/member/join")
	public String join(@ModelAttribute MemberDTO memberDTO) {
		System.out.println("memberDTO : " + memberDTO);
		memberService.save(memberDTO);
		return "redirect:/member/login";
	}
	// 회원 목록
	@GetMapping("/member/list")
	public String getList(Model model){
		// 브라우저 화면에는 MemberDTO가 나오고, 꺼낼때는 entity로 꺼내기
		List<MemberDTO> memberDTOList = memberService.findAll();
		model.addAttribute("memberList", memberDTOList);
		return "/member/list";
	}
	
	// 회원 상세보기
	//@PathVariable - 경로에 변수를 할당
	@GetMapping("/member/{id}")
	public String getMember(@PathVariable Long id, Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO);
		return "/member/detail";
	}
	// 회원 삭제하기
	@GetMapping("/member/delete/{id}")
	public String deleteMember(@PathVariable Long id) {
		memberService.deleteById(id);
		return "redirect:/member/list";
	}
	
	// 로그인 페이지 
	@GetMapping("/member/login")
	public String loginForm() {
		return "/member/login";
	}
	
	// 로그인 처리
	@PostMapping("/member/login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
		MemberDTO loginMember = memberService.login(memberDTO);
		// 로그인한 결과(객체가 있으면 로그인 되고, 객체가 없으면 로그인 폼으로..)
		if(loginMember != null) {
			// 세션발급 > HttpSession session으로..
			session.setAttribute("sessionEmail", loginMember.getMemberEmail());
			session.setAttribute("sessionName", loginMember.getMemberName());
			return "/main";
		}else {
			String error = "아이디나 비밀번호를 확인해 주세요 !";
			model.addAttribute("error", error);
			return "/member/login";
		}
	}
	
	// 로그아웃 처리
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate(); //세션삭제!
		return "redirect:/";
	}
	
	// 회원정보 수정페이지
	@GetMapping("/member/update")
	public String updateForm(HttpSession session, Model model) {
		String email = (String)session.getAttribute("sessionEmail");
		MemberDTO memberDTO = memberService.findByMemberEmail(email);
		model.addAttribute("member", memberDTO);
		return "/member/update";
	}
	// 수정 처리하기
	@PostMapping("/member/update")
	public String update(MemberDTO memberDTO) {
		memberService.update(memberDTO);
		return "redirect:/member/" + memberDTO.getId(); //상세페이지
	}
	// 이메일 중복 검사
	@PostMapping("/member/check-email")
	public @ResponseBody String checkEmail(@RequestParam("memberEmail") String memberEmail) {
		String resultText = memberService.checkEmail(memberEmail);
		return resultText;
	}
	
	
}
