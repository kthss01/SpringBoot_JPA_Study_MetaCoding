package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
/*
 * ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
 */
@Entity // User 클래스가 MySQL에 테이블이 생성됨
public class User {

	@Id // Primary key
	// 프로젝트에 연결된 DB의 넘버링 전략을 따라간다.
	// auto sequence 이런건 mysql oracle에 맞춰서 하게 됨
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 시퀀스, auto_increment

	@Column(nullable = false, length = 30)
	private String userName; // 아이디

	// 패스워드 100이나 주는 이유
	// 123456 => 해쉬 (비밀번호 암호화) 할거라서 넉넉하게 준거
	@Column(nullable = false, length = 100)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;

	// admin, user, manager => string이면 오타날 수 있음
	@ColumnDefault("'user'")
	private String role; // Enum 쓰는게 좋음 => 도메인 설정 할 수 있음 
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
}
