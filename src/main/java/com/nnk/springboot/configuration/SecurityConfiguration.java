package com.nnk.springboot.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

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
		    	 .anyRequest().permitAll()

		)
		.formLogin(withDefaults())
		.exceptionHandling(handling -> handling.accessDeniedPage("/app/error"));

		return http.build();
		//@formatter:on

	}

}
