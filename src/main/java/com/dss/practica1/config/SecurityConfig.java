package com.dss.practica1.config;


import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean 
	public SecurityFilterChain securityFilterChain( 
			HttpSecurity http) throws Exception { 
		http 
		.authorizeHttpRequests(auth -> auth 
				.requestMatchers("/").permitAll() 
				.requestMatchers(HttpMethod.GET, "/cart").permitAll() 
				.requestMatchers(HttpMethod.GET, "/products").permitAll()
				.requestMatchers(HttpMethod.GET, "/products/busqueda").permitAll()
				.requestMatchers("/products/**").hasRole("ADMIN")
				.requestMatchers("/export/**").hasRole("ADMIN")
				.requestMatchers("/cart/**").hasAnyRole("ADMIN", "USER")
				.requestMatchers(PathRequest.toH2Console()).permitAll()
				.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
				.requestMatchers("/api/**").hasRole("ADMIN")
				.anyRequest().authenticated() 
				) 
		.formLogin(form -> form 
				.loginPage("/login").permitAll() 
				) 
		.httpBasic(Customizer.withDefaults())
		.logout(logout -> logout 
				.logoutUrl("/logout") 
				.logoutSuccessUrl("/")
				) 
		.csrf(csrf -> csrf 
				.ignoringRequestMatchers(PathRequest.toH2Console())
				.ignoringRequestMatchers("/api/**")
				) 
		.headers(headers -> headers 
				.frameOptions(frame -> frame.sameOrigin()) 
				); 
		return http.build(); 
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService users(PasswordEncoder encoder) {
	    UserDetails admin = User.withUsername("admin")
	            .password(encoder.encode("admin"))
	            .roles("ADMIN")
	            .build();

	    UserDetails user = User.withUsername("user")
	            .password(encoder.encode("user"))
	            .roles("USER")
	            .build();

	    return new InMemoryUserDetailsManager(admin, user);
	}
}
