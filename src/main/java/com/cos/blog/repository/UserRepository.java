package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Users;


// JSP의 DAO
// 자동으로 bean등록이 된다.
// @Repository 생략가능
public interface UserRepository extends JpaRepository<Users, Integer> {
	
}
