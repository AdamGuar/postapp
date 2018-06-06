package com.app.postappclient.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private FluxAuthenticationProvider authProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/data/**")
			.permitAll()
		.antMatchers("/dist/**")
			.permitAll()
		.antMatchers("/vendor/**")
			.permitAll() 	
        .anyRequest()
        	.authenticated()
        .and()
        .formLogin()
        	.loginPage("/login")
        	.permitAll()
        .and()
        .logout()                                    
        	.permitAll();

	}
}
