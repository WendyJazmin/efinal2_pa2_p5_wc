package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter {
	
	private static  final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			String jwt = this.parseJwt(request);
			
			//VALIDACION
			if(jwt != null && this.jwtUtils.validateJwtToken(jwt)) {
				//si pasa la validación, vamos a generar una autorización
				//necesitamos el nombre a partir del token
				String userName = this.jwtUtils.getuserNameFromjwtToken(jwt);
				
				UsernamePasswordAuthenticationToken authentication =
			            new UsernamePasswordAuthenticationToken(
			            		userName,
			                null,
			                new ArrayList<>());
			        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		 
			        SecurityContextHolder.getContext().setAuthentication(authentication);
			
			}
		
		}catch(Exception e) {
			LOG.error("ERRRRO",e);
		}
		
		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {//el body y la cabecera viajan en el request
		String hearderAuth = request.getHeader("Authorization");
		
		if(StringUtils.hasText(hearderAuth) && hearderAuth.startsWith("Bearer ")) {//hasText, valida si es un texto válido, que no sea nulo
			return hearderAuth.substring(7, hearderAuth.length());
		}
		return null;
	}
}
