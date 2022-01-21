package com.codegym.furamaresortmanagement.service.employee.impl;

import com.codegym.furamaresortmanagement.model.employee.AppUser;
import com.codegym.furamaresortmanagement.model.employee.Role;
import com.codegym.furamaresortmanagement.repository.employee.IUserRepository;
import com.codegym.furamaresortmanagement.service.employee.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser =iUserRepository.findById(email).orElse(null);

        if (appUser == null){
            System.out.println("User not found! " + email);
            throw new UsernameNotFoundException("User" + email + " was not found in the database.");
        }

        System.out.println("Found user: " + email);
        Set<Role> roles = appUser.getRoles();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if (roles != null){
            for (Role role : roles) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
                grantedAuthorities.add(grantedAuthority);
            }
        }

        UserDetails userDetails = new User(appUser.getEmail(), appUser.getPassword(), grantedAuthorities);

        return userDetails;
    }


}
