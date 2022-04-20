package com.example.springdocumentsocket.security;

import com.example.springdocumentsocket.model.User;
import com.example.springdocumentsocket.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(username).orElseThrow(
                () -> new IllegalArgumentException(username + "을 찾을 수 없습니다.")
        );
        return new UserDetailsImpl(user);
    }
}
