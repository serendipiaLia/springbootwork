package com.khit.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khit.board.entity.User;
import com.khit.board.exception.CustomException;
import com.khit.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	// 회원 가입 
	public void save(User user) {
		userRepository.save(user);
	}
	// 회원 목록 보기
	public List<User> findAll() {
		return userRepository.findAll();
	}
	// 회원 상세보기 (id로 검색)
	public User findById(Integer id) {
		// 검색된 회원이 없을 경우 예외 반환 > 람다식으로 간단하게 표현 
		User findUser = userRepository.findById(id).orElseThrow(() -> {
			return new CustomException(id + "번 회원은 없습니다.");
		});
		return findUser;
	}
	// 검색된 회원이 없을 경우 예외 반환
//      public User findById(Integer id) {
//		User findUser = userRepository.findById(id).orElseThrow(new Supplier<CustomException>() {
//
//			@Override
//			public CustomException get() {
//				return new CustomException(id + "번 회원은 없습니다.");
//			}
//		});
//		return findUser;
// }
	
	// 회원 수정
	public void update(User user) {
		userRepository.save(user);
	}
	// 회원 삭제
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}
		
	
}
