package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Boards;

public interface BoardRepository extends JpaRepository<Boards,Integer> {

}