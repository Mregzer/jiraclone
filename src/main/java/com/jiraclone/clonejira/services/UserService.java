package com.jiraclone.clonejira.services;

import com.jiraclone.clonejira.repositories.UserRepository;
import com.jiraclone.clonejira.variabels.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder encoder(){return new BCryptPasswordEncoder();}

    public void createUser(User user){
        user.setPassword(encoder().encode(user.getPassword()));
        userRepository.save(user);
    }
}
