package com.example.demo.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

@Configuration
public class OAuth2SecurityConfig extends WsConfigurerAdapter {

	 
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        
            .authorizeRequests()
                .requestMatchers("/api/.*").authenticated() // Use regexMatchers
                .and()
            .oauth2ResourceServer()
                .jwt();
	    }
	
}
