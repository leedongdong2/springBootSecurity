package com.example.springBootTest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

/**
 * 
 * @author must1
 * 시큐리티 로그인 설정
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	
	@Autowired
	public OnAuthenticationSuccess securityConfig;
	
    private static final String[] AUTH_WHITELIST = {
            "/","/user/**"
    };
	
	
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable().cors().disable()
            .authorizeHttpRequests(authorize -> authorize
	                    .shouldFilterAllDispatcherTypes(false)
	                    //모든권한 접근가능
	                    .requestMatchers(AUTH_WHITELIST)
	                    .permitAll()
	                    //어드민인 권한을 가진자만 접근 가능
	                    .requestMatchers("/admin/**")
	                    .hasRole("ADMIN")
	                    .requestMatchers("/main/**")
	                    .hasRole("USER")
	                    .anyRequest()
	                    .authenticated()
	                    
                    )
	                .formLogin(login -> login	// form 방식 로그인 사용
	                		//로그인 페이지 설정
	                		.loginPage("/")
	                		//시큐리티 로그인을 실행할 url
	                		.loginProcessingUrl("/login_proc")
	                		//로그인 성공시 할 커스텀설정
	                		.successHandler(securityConfig)
	                		//로그인 실패시 갈 url
	                		.failureUrl("/main/assetDenied")
	                        .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
	                )
	                .logout()
	                //로그아웃 url
	                .logoutUrl("/logout")
	                //로그아웃 성공시 갈 페이지
	                .logoutSuccessUrl("/")
	        		.and()
	        		.rememberMe()
	        		.and()
	        		//세션 매니져 
	        		.sessionManagement()
	        		//세션 최대 갯수
	        		.maximumSessions(1)
	        		//중복 로그인시 세션을 삭제 할지 그대로 둘지 설정
	        		// false 이전 세션을 삭제
	        		.maxSessionsPreventsLogin(false);
	        		
	        return http.build();
	    }
	  
	  
	  //암호화
	  @Bean
	  public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
}




