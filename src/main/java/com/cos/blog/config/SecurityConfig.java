package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;


//빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 해주는 것
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // IoC가 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인을 해주는데, password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
		// 패스워드 인코더가 encodePWD라는 것을 null 자리의 오브젝트에게 알려줘야한다.
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf토큰 비활성화(테스트시 걸어두는게 좋음) 
			.authorizeRequests()
			.antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
			.permitAll()
			.anyRequest() // 이게 아닌 모든 요청은
			.authenticated() //인증이 필요
		.and()
			.formLogin()
			.loginPage("/auth/loginForm")
			.loginProcessingUrl("/auth/loginProc")
		// 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인한다.
		// 가로채서 로그인할 때 그 때 만들어야할 클래스가 있다.
		// userdetails타입의 user오브젝트를 만들어줘야한다.
		// user오브젝트 타입을 맞춰줘야 가로챌 수 있다.
			.defaultSuccessUrl("/");
		//로그인 성공 후에 요청되는 페이지
			// .failureUrl("로그인 실패 후 요청 페이지")
	}
}