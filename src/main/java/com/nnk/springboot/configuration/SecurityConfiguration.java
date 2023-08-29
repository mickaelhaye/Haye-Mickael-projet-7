package com.nnk.springboot.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

/**
 * this class is to manage the security with spring security.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	private static Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

	/**
	 * this method consists of managing the filter chain.
	 * 
	 * @param http security
	 * @return http build
	 * @throws Exception not found
	 */
	//@formatter:off
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		logger.debug("securityFilterChain");
		http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
						.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()		
		           		.requestMatchers("/").permitAll()
                		.requestMatchers("/app/secure/article-details").permitAll()
                		.requestMatchers("/user/*").permitAll()
                		.requestMatchers("/user/*/*").authenticated()
                		.anyRequest().hasRole("ADMIN"))

                .formLogin(login -> login.permitAll()
                        .loginPage("/app/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/app/login_success")
                        .failureUrl("/app/login_error"))
                .logout(logout -> logout.permitAll()
                		.logoutUrl("/app-logout")
                		.logoutSuccessUrl("/logout_success")
                		.deleteCookies("remove"))
                .exceptionHandling(handling -> handling.accessDeniedPage("/app/error"));

		return http.build();
		//@formatter:on

	}

}
