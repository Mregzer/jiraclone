package com.jiraclone.clonejira.services;

import com.jiraclone.clonejira.Exceptions.EmailAlreadyExistsException;
import com.jiraclone.clonejira.repositories.UserRepository;
import com.jiraclone.clonejira.variabels.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional  // Обеспечивает атомарность транзакции
    public void createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()){
            // Хэшируем пароль перед сохранением
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // Сохраняем пользователя
            userRepository.save(user);
        } else throw new EmailAlreadyExistsException("Given email already user for other account");
    }

    public User getUserByEmail() {
        return getUserByEmail();
    }
}
