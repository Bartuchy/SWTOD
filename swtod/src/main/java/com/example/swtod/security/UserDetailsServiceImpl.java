package com.example.swtod.security;

import com.example.swtod.user.User;
import com.example.swtod.configs.exception.UserNotFoundException;
import com.example.swtod.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("User with username '%s' not found", username)));

        return new UserDetailsImpl(user);
    }
}
