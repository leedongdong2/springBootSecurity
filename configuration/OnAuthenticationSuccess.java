package com.example.springBootTest.configuration;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * 
 * @author must1
 * 정상 로그인시 커스텀 핸들러 구현
 *
 */
@Configuration
public class OnAuthenticationSuccess implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	
		String url = "/main/home";
		
		List<GrantedAuthority> authList = (List<GrantedAuthority>) authentication.getAuthorities();
		
		
		/*로그인시 권한에 따른 페이지 변환
		 * */
		if(authList.get(0).getAuthority().equals("ROLE_ADMIN")) {
			url = "admin/admin";
		};

		
		response.sendRedirect(url);
	}

}
