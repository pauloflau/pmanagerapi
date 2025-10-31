package com.jmp.pmanager.infrastructure.security;

import java.util.Objects;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService {

	private final static String AUTH_TOKEN_HEADER_NAME = "x-api-key";

	public Authentication getAuthentication(HttpServletRequest request){ 
	        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME); 

	        if(!Objects.equals(apiKey, "teste")){ 
	            throw new BadCredentialsException(("Chave de API Invalida " + apiKey));
	        }

	        return new ApiKeyAuthentication(apiKey);
	}
}
