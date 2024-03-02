package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;


@Component
public class JwtUtils {//con esto se valida el token
	
	private static  final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);
	
	//aqui solo llega el token
	public boolean validateJwtToken(String authToken) {
		
		try {
			Jwts.parser().setSigningKey("fckmsdmfmisofsfosofifisdefikiregi9483r84ruff8ufru87rg8ww322rrrrfsemillawdefrfgdtvgbomgobmdrb203290324825835824202405385358035edscfsdfsdfds3053035").parseClaimsJws(authToken);
			return true;
		}catch(Exception e){
			LOG.error("ERROR",e);
		}
		
		return false;
	}
	
	//metodo para obtener el nombre del usuario
	public String getuserNameFromjwtToken(String token) {
		return Jwts.parser().setSigningKey("fckmsdmfmisofsfosofifisdefikiregi9483r84ruff8ufru87rg8ww322rrrrfsemillawdefrfgdtvgbomgobmdrb203290324825835824202405385358035edscfsdfsdfds3053035").parseClaimsJws(token).getBody().getSubject();//setSigningKey(""), aqui se tiene que poner la semilla

	}

}
