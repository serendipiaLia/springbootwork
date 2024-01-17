package com.khit.board.dto;

import com.khit.board.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드가 들어가있는 생성자(매개변수)
@Builder
@Data
public class MemberDTO {

	private Long id;
	private String memberEmail;
	private String memberPassword;
	private String memberName;
	private Long memberAge; 
	
	
	// Entity를 DTO로 변환하는 정적 메서드
	public static MemberDTO toSaveDTO(Member member) {
//		MemberDTO memberDTO = new MemberDTO();
//		memberDTO.setId(member.getId());
//		memberDTO.setMemberEmail(member.getMemberEmail());
//		memberDTO.setMemberPassword(member.getMemberPassword());
//		memberDTO.setMemberName(member.getMemberName());
//		memberDTO.setMemberAge(member.getMemberAge());
//		
//		return memberDTO;
		
		// 위 메소드를 builder()방식으로 사용
		MemberDTO memberDTO = MemberDTO.builder()
				.id(member.getId())
				.memberEmail(member.getMemberEmail())
				.memberPassword(member.getMemberPassword())
				.memberName(member.getMemberName())
				.memberAge(member.getMemberAge())
				.build();
				
				return memberDTO;
		
	}
}
