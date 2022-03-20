package com.cos.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// html 파일이 아니라 data를 리턴해주는 controller = restController
@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	// http://localhost:8090/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// 한 페이지 당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(
			@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) 
				Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		// 이런 분기 처리도 가능
//		if (pagingUser.isFirst()) {
//			
//		}
		
		List<User> users = pagingUser.getContent();
		return users;
	}
//	public Page<User> pageList(
//			@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) 
//			Pageable pageable) {
//		Page<User> users = userRepository.findAll(pageable);
//		return users;
//	}
	
	// {id} 주소로 파라미터를 전달 받을 수 있음
	// http://localhost:8090/blog/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// Optional을 리턴한다고 함
		// 못찾는 경우 null 리턴 되기 때문에
		// -> Optional로 User 객체를 감싸서 가져오는거 null인지 아닌지 판단할 수 있음
//		User user = userRepository.findById(id).get(); // null 아닐꺼라는 생각
		
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//			// 인터페이스 
//			@Override
//			public User get() {
//				return new User();
//			}
//		}); // 없으면 만들어주라는거 Supplier 들어감
		
		// throws 날리는것도 있음 
		// IllegalArgumentException 
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//			@Override
//			public IllegalArgumentException get() {
//				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
//			}
//		});
		
		// 람다식
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
		});
		
		// 요청 : 웹 브라우저
		// user 객체 = 자바 오브젝트
		// -> 변환 (웹 브라우저가 있는 데이터로 데이터) = json
		// Gson 라이브러리 사용하고 그랫는데 
		// Springboot는 MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리 호출해서
		// user 오브젝트 json으로 변환해서 브라우저에 리턴해줌
		
		return user;
	}
	
	// http://localhost:8090/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	// @ReuqestParam으로 쓰면 뒤에 파라미터 다르게 쓸 수 있음
//	public String join(@RequestParam("username") String username, String password, String email) {
	public String join(User user) {
		// key = value (약속된 규칙)
		
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
//		user.setRole("user");
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
}
