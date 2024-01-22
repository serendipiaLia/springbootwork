package com.khit.board.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude="member")
@Table(name="t_board")
@Data
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(length=2000, nullable=false)
	private String content;
	
	@CreationTimestamp
	private Timestamp createdDate;
	
	//Board entity와 연관 관계 mapping
	// 다대일 매핑 (fetch는 조회할 때 사용 > EAGER은 전체조회 LAZY는 특정 조회)
	//JoinColumn - 외래키 설정
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id") // Member에 있는 PK인 member_id와 조인!
	private Member member;
	
}
