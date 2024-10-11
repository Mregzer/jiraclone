package com.jiraclone.clonejira.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String main(String email) {
        return "redirect:/login";
    }

    @GetMapping("/success")
    public String hello() {
        return "success";
    }
}
