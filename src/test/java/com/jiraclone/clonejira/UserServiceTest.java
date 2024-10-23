package com.jiraclone.clonejira;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.jiraclone.clonejira.Enums.Department;
import com.jiraclone.clonejira.repositories.UserRepository;
import com.jiraclone.clonejira.services.UserService;
import com.jiraclone.clonejira.variabels.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder; // Мокаем BCryptPasswordEncoder

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        // Настраиваем поведение мока для passwordEncoder
        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("hashedPassword");
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setDepartment(Department.DEVELOPMENT);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date birthdate = sdf.parse("31.08.1989");
        user.setBirthdate(birthdate);

        userService.createUser(user);

        verify(userRepository, times(1)).save(any(User.class));


        assertEquals("hashedPassword", user.getPassword()); // Проверяем, что пароль был захеширован
    }
}
