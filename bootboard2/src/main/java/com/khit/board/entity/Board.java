package com.khit.board.entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude="member")
@Table(name="boardtable")
@Entity
public class Board extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(length=2000, nullable=false)
	private String content;
	
	//Board entity와 연관 관계 mapping
	// 다대일 매핑 (fetch는 조회할 때 사용 > EAGER은 전체조회 LAZY는 특정 조회)
	//JoinColumn - 외래키 설정
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id") // >> Member에 있는 PK인 member_id와 조인!
	private Member member;
	
	// 1개의 페이지의 주인은 댓글 reply ('다'쪽이 주인)이므로 mappedBy 해주기
	@OneToMany(mappedBy = "board", cascade=CascadeType.ALL) // cascade=CascadeType.ALL : 게시글 삭제되면 해당 게시글 댓글도 모두 삭제
	@OrderBy("id desc")
	private List<Reply> replyList;
	
}
