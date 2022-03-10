package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO 라고 생각
// 자동으로 bean 등록이 된다
// @Repository // 생략해도 됨
public interface UserRepository extends JpaRepository<User, Integer> {

}
