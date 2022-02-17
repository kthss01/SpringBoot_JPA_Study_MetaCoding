package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Getter
//@Setter
@Data // getter setter 둘다
@NoArgsConstructor // 빈생성자
//@AllArgsConstructor // 생성자
@ToString
//@RequiredArgsConstructor
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;

//	private final int id;
//	private final String username;
//	private final String password;
//	private final String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

}
