package com.khit.board.entity;

import java.util.ArrayList;
import java.util.List;

import com.khit.board.dto.MemberDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString(exclude = "boardList")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_table")
@Entity
public class Member extends BaseEntity{
	
	@Id                                                   // PK (Primary Key)
	@GeneratedValue(strategy = GenerationType.IDENTITY)   // 자동순번
	private Integer id;                                   // 회원 번호
	
	@Column(name = "member_id", unique = true)            // FK (Foreign Key) - 관계 맺기 위함 & 중복 X
	private String memberId;                              // 아이디 (로그인)
	
	@Column(nullable = false)                             // Not Null
	private String password;                              // 비밀번호 (로그인)
	
	@Column(nullable = false, length = 30)                // Not Null & 최대 30자
	private String name;                                  // 이름
	
	// @Column
	// private String role;                               // 권한
	@Enumerated(EnumType.STRING)
	private Role role;
	
	// Board와 관계 매핑 - @OneToMany : 한 명의 회원은 여러 개의 글을 작성할 수 있다.
	// 주인 설정 필요 - mappedBy : 다(多)쪽이 주인
	// 삭제 - cascade : 회원이 삭제되면 작성한 글 또한 함께 삭제된다.
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<>();
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Reply> replyList;
	
	
	
	// DTO(view에 온 입력값) -> Entity(DB에 저장(
	// 회원가입(id)가 자동 생성 되므로 id는 제외
	public static Member toSaveEntity(MemberDTO memberDTO) {
		Member member = Member.builder()
							  .memberId(memberDTO.getMemberId())
							  .password(memberDTO.getPassword())
							  .name(memberDTO.getName())
							  .role(memberDTO.getRole())
							  .build();
		
		return member;
	}
	// 회원 수정시에는 id가 존재하므로 id 불러오기
	public static Member toSaveUpdate(MemberDTO memberDTO) {
		Member member = Member.builder()
				.id(memberDTO.getId())
				.memberId(memberDTO.getMemberId())
				.password(memberDTO.getPassword())
				.name(memberDTO.getName())
				.role(memberDTO.getRole())
				.build();
		
		return member;
	}

}