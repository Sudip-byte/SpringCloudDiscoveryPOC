package com.sudip.users.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sudip.users.service.UsersService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private Environment environment;
	private UsersService usersService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public WebSecurity(Environment environment,UsersService usersService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.environment = environment;
		this.usersService = usersService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"))
		.and()
		.addFilter(getAuthentcationFilter());
	}

	private Filter getAuthentcationFilter() throws Exception {
		
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(usersService, environment,authenticationManager());
		
		//authenticationFilter.setAuthenticationManager(authenticationManager());
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		
		return authenticationFilter;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
		
	}

}
