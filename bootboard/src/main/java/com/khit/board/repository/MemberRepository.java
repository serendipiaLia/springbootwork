package com.khit.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.entity.Member;

// JPARepository를 상속 받아야함 (@Repository 어노테이션 생략 가능)
public interface MemberRepository extends JpaRepository<Member, Long> {
	// 제공된 메서드 - save(), findAll(), findById(), deleteById()
	
	// 쿼리 메서드 (메서드 이름이 쿼리를 나타냄) - Optional(null 체크 클래스 > java util제공)
	// select * from table_member where member_email = ?
	 Optional<Member> findByMemberEmail(String memberEmail);
}
