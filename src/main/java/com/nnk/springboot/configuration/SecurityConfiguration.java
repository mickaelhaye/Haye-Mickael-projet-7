package com.nnk.springboot.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//@formatter:off
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                        		.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        		.requestMatchers("/*").permitAll()
                        		.requestMatchers("/bidList/*").permitAll()
                        		.requestMatchers("/bidList/update/{id}").permitAll()
                        		.requestMatchers("/bidList/delete/{id}").permitAll()
                        		.requestMatchers("/curvePoint/*").permitAll()
                        		.requestMatchers("/curvePoint/update/{id}").permitAll()
                        		.requestMatchers("/curvePoint/delete/{id}").permitAll()
                        		.requestMatchers("/rating/*").permitAll()
                        		.requestMatchers("/rating/update/{id}").permitAll()
                        		.requestMatchers("/rating/delete/{id}").permitAll()
                        		                           
                )
                .formLogin(withDefaults())
                .rememberMe(withDefaults());
               
 					
                
		return http.build();
		//@formatter:on

	}

}
