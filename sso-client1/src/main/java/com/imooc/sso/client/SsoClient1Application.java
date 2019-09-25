/**
 * 
 */
package com.imooc.sso.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhailiang
 *
 */
@SpringBootApplication
@RestController
@EnableOAuth2Sso
public class SsoClient1Application {
	
	@GetMapping("/user")
	public Authentication user(Authentication user) {
		return user;
	}

	@RequestMapping("/hello")
	public String getHello(){
		return  "hello";
	}

	public static void main(String[] args) {
		SpringApplication.run(SsoClient1Application.class, args);
	}
	
}