package com.tripleh.triplehapp.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tripleh.triplehapp.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tripleh.triplehapp.entity.UserRole;


@Service("userService")
public class UserService implements UserDetailsService{
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.tripleh.triplehapp.entity.User user= userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
	return buildUser(user, authorities);
	}

	private User buildUser(com.tripleh.triplehapp.entity.User user, List<GrantedAuthority> authorities) {
		
	return new User(user.getUsername(), user.getPassword(), user.isEnabled(), 
			true, true, true, authorities);
	}

	
	private List<GrantedAuthority> buildAuthorities (Set<UserRole> userRole){
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

		for(UserRole userRol : userRole) {
			auths.add(new SimpleGrantedAuthority(userRol.getRole()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}
	
	
}

