package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)

@RestController
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m1 = new Member(1, "ssar", "1234", "email");
		Member m2 = new Member();
		System.out.println(TAG + " getter : " + m1.getId());
		m1.setId(5000);
		System.out.println(TAG + " setter : " + m1.getId());
		
		Member m = Member.builder().username("ssar").password("1234").email("email").build();
		System.out.println(m);
		
		return "lombok test 완료";
	}
	
	// 인터넷 브라우저 요청은 get 요청만 가능함
	
	// http://localhost:8090/http/get (select)
	@GetMapping("/http/get")
//	public String getTest(@RequestParam int id, @RequestParam String username) {
	public String getTest(Member m) {
		// id=1&username=ssar&password=1234&email=ssar@nate.com // 스프링이 Member 객체의 넣어줌
		
		return "get 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// http://localhost:8090/http/post (insert)
	@PostMapping("/http/post")
//	public String postTest(Member m) {
//		return "post 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
//	}
	
//	public String postTest(@RequestBody String text) { // text/plain
//		return "post 요청 " + text;
//	}
	
	// MessageConverter (스프링부트)가 해줌
	public String postTest(@RequestBody Member m) { // application/json
		return "post 요청 " + m.toString();
	}
	
	// http://localhost:8090/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 " + m.toString();
	}
	
	// http://localhost:8090/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
