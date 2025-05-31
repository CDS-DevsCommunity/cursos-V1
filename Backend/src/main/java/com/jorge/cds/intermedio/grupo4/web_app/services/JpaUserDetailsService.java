package com.jorge.cds.intermedio.grupo4.web_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jorge.cds.intermedio.grupo4.web_app.entities.User;
import com.jorge.cds.intermedio.grupo4.web_app.repositories.UserRepository;
import com.jorge.cds.intermedio.grupo4.web_app.security.CustomUserDetails;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepository.findByUserName(username) // O findByFullName(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Usuario %s no existe en el sistema!", username)));

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userService.userRole(user)));

        return new CustomUserDetails(user, authorities);
    }

}
