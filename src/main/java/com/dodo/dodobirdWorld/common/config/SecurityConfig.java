package com.dodo.dodobirdWorld.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import com.dodo.dodobirdWorld.login.service.UserLoginFailHandler;

@Configuration
@EnableWebSecurity 
public class SecurityConfig {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	
	// HttpSecurity 
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				// 각각의 사용자의 권한으로 시장하는 매핑 각각의 사용자가 가진 권한에 맞춰서 적용
				.antMatchers("/").permitAll()
				.antMatchers("/board").permitAll()
				.antMatchers("/board/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
				.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.and()
					.headers()
					.addHeaderWriter(new XFrameOptionsHeaderWriter(
							XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
				.and()
					.formLogin()
					.loginPage("/login") // 로그인 페이지
					.loginProcessingUrl("/login") // post method로 보내기 > formLogin방식으로 controller를 거치지 않고 알아서 시큐리티로 param전달
                    .usernameParameter("id") // id/pw param으로 담아 시큐리티로 보냄
                    .passwordParameter("password")
					.successForwardUrl("/login_success") // 로그인 성공 시 포워드되는 URL (세션값 등 지정) login_success_handler
//					.failureForwardUrl("/access_denied") // 로그인 실패 시 별도로 처리 필요할 경우
					.failureHandler(userLoginFailHandler())   // 로그인 실패 후 커스텀 로그인 실패 핸들러 설정 >> 핸들러 설정 시, forwardurl 무시됨. 
					.permitAll() // 모든 사용자에게 허용
				.and()
					.csrf().disable() // CrossSite Request Forgery (disable 해주지 않을 경우 POST 매핑에서 문제 발생)
				.logout()
					.logoutUrl("/logout") // 로그아웃 요청 URL
					.logoutSuccessUrl("/") // 로그아웃이 성공하면 다시 로그인 페이지로 돌아감
					.invalidateHttpSession(true) // 세션 제거 기본값 true
					.deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제 허용
		;
		
		return http.build();
	}
	
	// WebSecurity 
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // 시큐리티 검사 무시 필터 목록 - 정적자원에 인증+인가를 할 필요가 없을 때 추가.
        return (web) -> web.ignoring().antMatchers("/static/css/**", "/static/img/**");
    }
	
	// 로그인 실패 핸들러 등록
	@Bean
	public UserLoginFailHandler userLoginFailHandler() {
		return new UserLoginFailHandler();
	}
	
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}


	
}