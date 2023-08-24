package com.nnk.springboot.configuration;

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
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                .requestMatchers("/bidList/*").authenticated()
                .requestMatchers("/bidList/update/{id}").authenticated()
                .requestMatchers("/bidList/delete/{id}").authenticated()
                .requestMatchers("/curvePoint/*").authenticated()
                .requestMatchers("/curvePoint/update/{id}").authenticated()
                .requestMatchers("/curvePoint/delete/{id}").authenticated()
                .requestMatchers("/rating/*").authenticated()
                .requestMatchers("/rating/update/{id}").authenticated()
                .requestMatchers("/rating/delete/{id}").authenticated()
                .requestMatchers("/ruleName/*").authenticated()
                .requestMatchers("/ruleName/update/{id}").authenticated()
                .requestMatchers("/ruleName/delete/{id}").authenticated()
                .requestMatchers("/trade/*").authenticated()
                .requestMatchers("/trade/update/{id}").authenticated()
                .requestMatchers("/trade/delete/{id}").authenticated()
                .requestMatchers("/user/*").permitAll()
                .requestMatchers("/user/update/{id}").authenticated()
                .requestMatchers("/user/delete/{id}").authenticated()
                .requestMatchers("/admin/home").hasRole("ADMIN")
                .anyRequest().permitAll())

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
