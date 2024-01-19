package com.khit.test.dto;

import com.khit.test.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberDTO {
	
	private Long id;
	private String username;
	private String password;
	private String email;
	
	// Entity를 DTO로 변환하는 정적 메서드
	public static MemberDTO toSaveDTO(Member member) {
		MemberDTO memberDTO = MemberDTO.builder()
				.id(member.getId())
				.username(member.getUsername())
				.password(member.getPassword())
				.email(member.getEmail())
				.build();
				
				return memberDTO;
	}
}
