package com.khit.board.entity;

import com.khit.board.dto.MemberDTO;

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
@Builder
@Data
@Table(name = "table_member") //mysql에서 table_member이 생성된 걸 확인 가능!
// DB역할을 하는 어노테이션 추가 
@Entity
public class Member {
	
	@Id  //PK(Primary Key기본키)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true) //유일성을 가짐, 중복검사
	private String memberEmail;
	
	@Column(nullable = false) //nullable == not null, 필수입력
	private String memberPassword;
	
	@Column(length = 30, nullable = false) // 길이 30byte
	private String memberName;
	
	@Column
	private Long memberAge; 
	
	// MemberDTO를 매개로 받아서 entity에 저장하는 정적메서드 생성 >> 회원가입에 사용
	public static Member toSaveEntity(MemberDTO memberDTO) {
//		Member member = new Member();
//		member.setMemberEmail(memberDTO.getMemberEmail()); //폼에서 입력한걸 가져오는 메서드
//		member.setMemberPassword(memberDTO.getMemberPassword());
//		member.setMemberName(memberDTO.getMemberName());
//		member.setMemberAge(memberDTO.getMemberAge());
//		
//		return member;
		
		// 위 메소드를 builder()로 생성 > 위에서 @Builder해줘야 함
		Member member = Member.builder()
				// 필드생성
				.memberEmail(memberDTO.getMemberEmail())
				.memberPassword(memberDTO.getMemberPassword())
				.memberName(memberDTO.getMemberName())
				.memberAge(memberDTO.getMemberAge())
				.build();
		
		return member;
	}
	// 회원 수정할 때 필요한 저장용 정적메서드! (id 포함)
	public static Member toUpdateEntity(MemberDTO memberDTO) {
		Member member = new Member();
		member.setId(memberDTO.getId());
		member.setMemberEmail(memberDTO.getMemberEmail()); //폼에서 입력한걸 가져오는 메서드
		member.setMemberPassword(memberDTO.getMemberPassword());
		member.setMemberName(memberDTO.getMemberName());
		member.setMemberAge(memberDTO.getMemberAge());
		
		return member;
	}
	
}
