package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터에 씀
	private String content; // 섬머노트 라이브러리 툴을 쓸 거 <html> 택가 섞여서 디자인됨
	
	@ColumnDefault("0")
	private int count; // 조회수
	
	// FetchType Eager -> 바로 가져온다는거
	// 기본 전략임 
	@ManyToOne(fetch = FetchType.EAGER) // Many : Board, One : User => 한명은 여러 게시글을 쓸 수 있음
	@JoinColumn(name = "userId")
	private User user; // DB는 오브젝트를 저장할 수 없음 => FK , 자바는 오브젝트를 저장할 수 있다.

	// mappedBy 연관관계의 주인이 아니다 (난 FK 아님) => DB에 컬럼 만들지 마세요
	// mappedBy 뒤의 board는 Reply의 필드이름을 적는거임 board 필드
	// 여긴 FetchType 기본이 LAZY라고 함
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
