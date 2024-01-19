package com.khit.board.entity;

import com.khit.board.dto.BoardDTO;

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
@Entity // 테이블 생성
@Table(name = "table_board")
public class Board extends BaseEntity{
	
	@Id //PK 기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String boardTitle;
	
	@Column(length = 30, nullable = false)
	private String boardWriter;
	
	@Column(length = 2000, nullable = false)
	private String boardContent;
	
	@Column
	private Integer boardHits; // 조회수
	
	// 파일
	@Column
	private String filename; //boardController에 있는  MultipartFile boardFile과 이름 다르게..
	@Column
	private String filepath;
	
	
	// DTO를 Entity로 변환하는 정적 메서드 
	public static Board toSaveEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				// 필드드 생성
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.filename(boardDTO.getFilename())
				.filepath(boardDTO.getFilepath())
				.boardHits(0)
				.build();
		return board;
	}
	// DTO를 Entity로 수정하여 변환하는 정적 메서드 
	public static Board toUpdateEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				// 필드드 생성
				.id(boardDTO.getId())
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.filename(boardDTO.getFilename())
				.filepath(boardDTO.getFilepath())
				.boardHits(boardDTO.getBoardHits())
				.build();
		return board;
	}
	
}
