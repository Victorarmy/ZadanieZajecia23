package com.example.demo.configuration;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        com.example.demo.model.User user = userRepository.findByEmail(login);

        User userDetails = new User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true,
                getAuthorities(user.getRoles()));

        return userDetails;
    }

    private Set<GrantedAuthority> getAuthorities(Set<UserRole> roles) {
        Set<GrantedAuthority> grantedAuthorities = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toSet());
        return grantedAuthorities;
    }
}
