package com.task.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	//TODO: do we really need to disable spring security's CSRF, will use JWT later
	//this is disabling the security for all these APIs
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll())
	      .csrf(AbstractHttpConfigurer::disable);
	    return http.build();
	}

}
