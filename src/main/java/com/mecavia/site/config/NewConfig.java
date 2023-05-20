package com.mecavia.site.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


//@Configuration
//@EnableWebSecurity
public class NewConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userService;
	
	PasswordEncoder passwordencoder = new BCryptPasswordEncoder();
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		 provider.setUserDetailsService(userService);
		 provider.setPasswordEncoder(passwordencoder);
		 auth.authenticationProvider(provider);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.csrf().disable()
			.authorizeRequests()
			.anyRequest()
//			.antMatchers("/**")
//			.fullyAuthenticated()
			.permitAll()
			.and()
			.httpBasic();
		
	}

}
