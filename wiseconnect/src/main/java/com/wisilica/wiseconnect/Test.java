package com.wisilica.wiseconnect;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Test {
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	public static void main(String[] args) {
//		System.out.println(new Test().passwordEncoder().encode("spring-security-oauth2-read-write-client-password1234"));
//		boolean result = 		new BCryptPasswordEncoder().matches("and@1234","$2a$10$WnTbtykbB/zOL5S19ygWOO/4EOeU7f0phBiLQsUu0gU78tvD8vrty");
//		if(result){
//			System.out.println("Matched");
//		}else{
//			System.out.println("Failed");
//		}
//	}
}
