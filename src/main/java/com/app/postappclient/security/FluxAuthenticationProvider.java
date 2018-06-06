package com.app.postappclient.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class FluxAuthenticationProvider implements AuthenticationProvider{
	
	@Value("${api.host}")
	private String apiHost;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		WebClient client = WebClient.create(apiHost+"user");
		
		 String name = authentication.getName();
	     String password = authentication.getCredentials().toString();
	     
	     User user = new User(name,password);
	     
	     BodyInserter<Object, ReactiveHttpOutputMessage> inserter
	     = BodyInserters.fromObject(user);
	     
	     Auth auth = client.post().uri("/auth")
					.body(inserter)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve().bodyToMono(Auth.class).block();
		
	     if(auth.isPasswordCorrect)
	    	 return new UsernamePasswordAuthenticationToken(
	                 name, password, new ArrayList<>());
	     else
	    	 throw new UserNotValidException("Password or email incorrect");
	}

	@Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
	

}
