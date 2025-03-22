package com.example.JWT_Learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.JWT_Learn.model.Human;
import com.example.JWT_Learn.repository.HumanRepository;

@Service
public class HumanDetailsService implements UserDetailsService {
    @Autowired
    private HumanRepository humanRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Human human = humanRepository.findByUsername(username);
        return new User(human.getUsername(), human.getPassword(),
                List.of(new SimpleGrantedAuthority(human.getRole())));
    }
}