package com.jiraclone.clonejira.controllers;

import com.jiraclone.clonejira.Enums.Department;
import com.jiraclone.clonejira.services.UserService;
import com.jiraclone.clonejira.variabels.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    // Страница логина
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Страница регистрации
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        // Передаем список департаментов в модель
        List<Department> departments = Arrays.asList(Department.values());
        model.addAttribute("departments", departments);

        return "registration";
    }

    // Обработка данных регистрации
    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model) {
        try {
            // Убедитесь, что все обязательные поля заполнены
            if (user.getUsername() == null || user.getEmail() == null || user.getPassword() == null || user.getDepartment() == null || user.getBirthdate() == null) {
                throw new IllegalArgumentException("All fields must be filled");
            }

            // Сохраняем пользователя
            userService.createUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace(); // Вывод ошибок в консоль для диагностики
            model.addAttribute("error", "Error during registration: " + e.getMessage());
            return "redirect:/registration?emailError";
        }
    }

}
