package com.khit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	// 게시판 글쓰기 처리
	public void save(BoardDTO boardDTO, MultipartFile boardFile) throws IllegalStateException, IOException {
		// 1. 파일을 서버에 저장하기
		if(!boardFile.isEmpty()) { //file이 비어있지 않으면,,,(=전달된 파일이 있으면)
			// 저장경로
			String filepath = "C:\\bootworks\\bootboard\\src\\main\\resources\\static\\upload\\"; 
								// static에 upload폴더 생성 후, properties 경로복사 후 upload뒤에 \\추가
			UUID uuid = UUID.randomUUID(); // 무작위 아이디 생성(중복 파일의 이름 생성)
			
			String filename = uuid + "_" + boardFile.getOriginalFilename(); // 원본파일
			
			// File클래스 객체 생성
			File savedFile = new File(filepath, filename); // upload폴더에 저장
			boardFile.transferTo(savedFile);
			
			//2. 파일 이름은 DB에 저장
			boardDTO.setFilename(filename);
			boardDTO.setFilepath("/upload/" + filename); // filepath(파일경로) 설정
		}
		// BoardDTO를 Entity로 변환
		Board board = Board.toSaveEntity(boardDTO);
		// Entity를 DB에 저장
		boardRepository.save(board);
	}
	// 글 목록 불러오기
	public List<BoardDTO> findAll() {
		// DB에서 Entity list를 가져옴
		List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		// 빈 리스트 생성
		List<BoardDTO> boardDTOList = new ArrayList<>();
		// 가져온 Entity를 DTO로 변환 
		for(Board board : boardList) {
			BoardDTO boardDTO = BoardDTO.toSaveDTO(board);
			boardDTOList.add(boardDTO);
		}
		return boardDTOList;
	}
	// 글 상세보기 
	public BoardDTO findById(Long id) {
		Optional<Board> findBoard = boardRepository.findById(id);
		if(findBoard.isPresent()) { // 찾는 게시글이 있으면, Controller로 보내줌
			BoardDTO boardDTO = BoardDTO.toSaveDTO(findBoard.get());
			return boardDTO;
		}else { // 찾는 게시글이 없으면 에러로 예외처리 
			throw new BootBoardException("게시글을 찾을 수 없습니다.");
		}
	}
	// BoardRepository에 updateHits 메서드 생성
	// 글 조회수!
	@Transactional // Controller에서 메서드(작업)가 2개 이상이면 사용!
	public void updateHits(Long id) { 
		boardRepository.updateHits(id);
	}
	// 글 삭제
	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}
	// 글 수정 처리
	public void update(BoardDTO boardDTO) {
		// save() - 삽입(id가 없고), 수정(id가 있음)
		Board board = Board.toUpdateEntity(boardDTO);
		boardRepository.save(board);
	}
	// 글 목록 페이지 처리
	public Page<BoardDTO> findListAll(Pageable pageable) {
		
		int page = pageable.getPageNumber() -1 ; //DB가 1 작아야함! 우리가 원하는건 1페이지부터 시작이지만 실제 데이터는 0페이지부터 시작이므로!
		int pageSize = 10;						// Controller에서 default값을 1로 설정 해줬기 때문,, 58행(page=1)
		
		// (page, pageSize) : 페이지 번호, 페이지 당 글 개수
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id"); 
		
		Page<Board> boardList =  boardRepository.findAll(pageable); // Entity를 꺼내와야 하므로 findAll()
		
		log.info("첫번째 페이지(isFirst) : " + boardList.isFirst());
		log.info("마지막 페이지(isLast) : " + boardList.isLast());
		log.info("페이지번호(getNumber) : " + boardList.getNumber());
		
		// 생성자 방식으로 boardDTOList 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board -> 
			new BoardDTO(board.getId(), 
					board.getBoardTitle(), 
					board.getBoardWriter(), 
					board.getBoardContent(),
					board.getBoardHits(), 
					board.getFilename(), 
					board.getFilepath(),
					board.getCreatedDate(), 
					board.getUpdatedDate()));
		
		return (Page<BoardDTO>) boardDTOList;
	}
	// 글 목록 제목 검색 처리! >> 검색어가 없으면 페이지 처리하고, 검색어가 있으면 검색어로 페이지 처리
	public Page<BoardDTO> findByBoardTitleContaining(String keyword, Pageable pageable) {
		int page = pageable.getPageNumber() -1 ; 
		int pageSize = 10;						
		
		// (page, pageSize) : 페이지 번호, 페이지 당 글 개수
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id"); 
		
		Page<Board> boardList =  boardRepository.findByBoardTitleContaining(keyword, pageable); // Entity를 꺼내와야 하므로 findAll()
		
		// 생성자 방식으로 boardDTOList 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board -> 
			new BoardDTO(board.getId(), 
						board.getBoardTitle(), 
						board.getBoardWriter(), 
						board.getBoardContent(), 
						board.getBoardHits(),
						board.getFilename(), 
						board.getFilepath(),  
						board.getCreatedDate(), 
						board.getUpdatedDate()));
		
		return boardDTOList;
	}
	// 글 목록 내용 검색 처리! >> 검색어가 없으면 페이지 처리하고, 검색어가 있으면 검색어로 페이지 처리
	public Page<BoardDTO> findByBoardContentContaining(String keyword, Pageable pageable) {
		int page = pageable.getPageNumber() -1 ; 
		int pageSize = 10;						
		
		// (page, pageSize) : 페이지 번호, 페이지 당 글 개수
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id"); 
		
		Page<Board> boardList =  boardRepository.findByBoardContentContaining(keyword, pageable); // Entity를 꺼내와야 하므로 findAll()
		
		// 생성자 방식으로 boardDTOList 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board -> 
			new BoardDTO(board.getId(), 
					board.getBoardTitle(), 
					board.getBoardWriter(), 
					board.getBoardContent(),
					board.getBoardHits(), 
					board.getFilename(), 
					board.getFilepath(),
					board.getCreatedDate(), 
					board.getUpdatedDate()));
		
		return boardDTOList;
	}
}
