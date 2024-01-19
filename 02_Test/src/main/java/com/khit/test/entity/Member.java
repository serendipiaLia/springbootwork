package com.khit.test.entity;

import com.khit.test.dto.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="table_member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String email;
	
	// MemberDTO를 매개로 받아서 entity에 저장하는 정적메서드 생성
	public static Member toSaveEntity(MemberDTO memberDTO) {
		Member member = Member.builder()
				.username(memberDTO.getUsername())
				.password(memberDTO.getPassword())
				.email(memberDTO.getEmail())
				.build();
		return member;
	}
	
}
