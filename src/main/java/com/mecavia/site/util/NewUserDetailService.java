package com.mecavia.site.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mecavia.site.entity.User;
import com.mecavia.site.repo.UserRepo;

public class NewUserDetailService implements UserDetailsService {
	@Autowired
	protected UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user  = userRepo.findByemail(username);
		if(user==null) throw new UsernameNotFoundException("USER 404");
		return new UserPrinciples(user);
	}
	
}
