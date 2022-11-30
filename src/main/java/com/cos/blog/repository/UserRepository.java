package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Users;


// JSP의 DAO
// 자동으로 bean등록이 된다.
// @Repository 생략가능
public interface UserRepository extends JpaRepository<Users, Integer> {
	
	// select * from user where username = ?;
	Optional<Users> findByUsername(String username);
	
	// 로그인을 위한 함수
	// JPA Naming 전략
	// select * from where username = ? AND password = ?;
//	Users findByUsernameAndPassword(String username, String password);
	
	// 위랑 같음
	// @Query(value = "select * from users where username = ; 1
	// AND password = ? 2", nativeQuery = true)
	// User login(String username, STring password);
}