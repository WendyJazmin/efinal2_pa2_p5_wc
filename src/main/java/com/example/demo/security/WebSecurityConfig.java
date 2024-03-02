package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
	private AuthEntryPointJwt authEntryPointJwt;//unauthorizedHandler
	
	@Bean
	public AuthTokenFilter autenticationJwtFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
					//.antMatchers("/api/auth/**").permitAll().antMatchers("/api/test/**").permitAll() //No hace falta porque todos deben autenticarse
					.anyRequest().authenticated();
	 
			http.addFilterBefore(autenticationJwtFilter(), UsernamePasswordAuthenticationFilter.class);
	 
			return http.build();
		}

}
