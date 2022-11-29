package com.cos.blog.test;

//import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data // Getter, Setter
//@AllArgsConstructor // 생성자
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

}
