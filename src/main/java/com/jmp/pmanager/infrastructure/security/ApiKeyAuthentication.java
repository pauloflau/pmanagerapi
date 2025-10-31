package com.jmp.pmanager.infrastructure.security;

import static org.springframework.security.core.authority.AuthorityUtils.NO_AUTHORITIES;

import org.springframework.security.authentication.AbstractAuthenticationToken;
public class ApiKeyAuthentication extends AbstractAuthenticationToken{

	private final String apiKey;

	public ApiKeyAuthentication(String apiKey) {
		super(NO_AUTHORITIES);
		this.apiKey = apiKey;
        setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {

		return null;
	}

	@Override
	public Object getPrincipal() {
		return apiKey;
	}
}
