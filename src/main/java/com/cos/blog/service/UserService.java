package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encodeer;
	
	@Transactional
	public void 회원가입(Users user) {
		user.setRoles(RoleType.USER);
		String rawPassword = user.getPassword();
		String encPassword = encodeer.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user); // 하나의 트렌젝션
	}
	
	// 회원정보 수정
	@Transactional
	public void 회원수정(Users user) {
		// 수정시에는 영속성 컨텍스트 Users 오브젝트를 영속화 시키고,
		// 영속화된 Users 오브젝트를 수정
		// select를 해서 Users 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.
		Users persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encodeer.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		// 회원수정 메서드 종료 = 서비스 종료 = 트랜잭션 종료 = commit
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹 되어 update문을 날림.
	}
	
//	@Transactional(readOnly = true)  // select 할 때 트렌젝션 시작, 서비스 종료시에 트렌젝션 종료(정합성)
//	public Users 로그인(Users user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}