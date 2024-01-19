package com.khit.board.dto;

import java.time.LocalDateTime;

import com.khit.board.entity.Board;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

	// Board.java > Entity..
	private Long id;
	
	@NotEmpty(message = "제목은 필수항목 입니다.")
	@Size(max = 255)
	private String boardTitle;
	
	@NotEmpty(message = "작성자는 필수항목 입니다.")
	@Size(max = 30)
	private String boardWriter;

	@NotEmpty(message = "내용을 입력해주세요!")
	@Size(max = 2000)
	private String boardContent;
	
	private Integer boardHits;
	
	//파일
	private String filename;
	private String filepath;
	
	// BaseEntity.java
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	
	// Entity > DTO로 변환할 정적 메서드
	public static BoardDTO toSaveDTO(Board board) {
		BoardDTO boardDTO = BoardDTO.builder()
				.id(board.getId())
				.boardTitle(board.getBoardTitle())
				.boardWriter(board.getBoardWriter())
				.boardContent(board.getBoardContent())
				.filename(board.getFilename())
				.filepath(board.getFilepath())
				.boardHits(board.getBoardHits())
				.createdDate(board.getCreatedDate())
				.updatedDate(board.getUpdatedDate())
				.build();
		return boardDTO;
	}
	
}
