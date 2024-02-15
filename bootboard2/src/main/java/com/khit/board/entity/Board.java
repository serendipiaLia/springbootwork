package com.khit.board.entity;

import java.util.List;

import com.khit.board.dto.BoardDTO;

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
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString(exclude="member")
@Table(name="board_table")
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
	
	// dto > entity (글쓰기)
	public static Board toSaveEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.title(boardDTO.getTitle())
				.content(boardDTO.getContent())
				.member(boardDTO.getMember())
				.build();
		return board;
	}
	// dto > entity (글 수정)
	public static Board toUpdateEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.id(boardDTO.getId()) // 이미 글 번호 존재하므로 id도 불러오기
				.title(boardDTO.getTitle())
				.content(boardDTO.getContent())
				.member(boardDTO.getMember())
				.build();
		return board;
	}
	
	
	
}
