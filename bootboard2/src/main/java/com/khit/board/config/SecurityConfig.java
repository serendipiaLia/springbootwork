
package com.khit.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customService;
	
	@Bean // @Bean은 프로젝트에서 관리가 안되는 클래스를 bean으로 사용할 때 필요!
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.userDetailsService(customService);
		
		// 인증 설정 -> 권한 설정
		http // http.authorizeHttpRequests(...) -> 메서드를 통해 HTTP 요청에 대한 권한 설정
		.authorizeHttpRequests(authorize -> authorize
								.requestMatchers("/", "/css/**", "/images/**" , "/js/**").permitAll()
								.requestMatchers("/board/write").authenticated()
								.requestMatchers("/member/**", "/board/**").permitAll()
								.anyRequest().authenticated() )
		.formLogin(form -> form.loginPage("/member/login").defaultSuccessUrl("/"));
														// defaultSuccessUrl("/") -> 로그인 성공 후 기본적으로 이동할 경로는 메인페이지
		http.logout()
			.logoutUrl("/member/logout")
			.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
			.invalidateHttpSession(true)
			.logoutSuccessUrl("/");
		
		return http.build(); // 위에서 설정한 보안 설정을 적용하고, 설정된 HttpSecurity 반환)
	}
	
	// 암호화 설정
	// PasswordEncoder를 상속 받은 BCryptPasswordEncoder를 반환
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); //List의 arrylist라고 생각하기
	}
	
	
}