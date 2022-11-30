package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		String rawPassword = user.getPassword();
		String encPassword = encodeer.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user); // 하나의 트렌젝션
	}
	
//	@Transactional(readOnly = true)  // select 할 때 트렌젝션 시작, 서비스 종료시에 트렌젝션 종료(정합성)
//	public Users 로그인(Users user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}