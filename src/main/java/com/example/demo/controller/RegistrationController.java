package com.example.demo.controller;

import com.example.demo.dao.RegistrationPersistenceService;
import com.example.demo.exception.AccountExistsException;
import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @ExceptionHandler(value = AccountExistsException.class)
    public String accountExists(RuntimeException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "registrationFailed";
    }

    private RegistrationPersistenceService registrationPersistenceService;

    @Autowired
    public RegistrationController(RegistrationPersistenceService registrationPersistenceService) {
        this.registrationPersistenceService = registrationPersistenceService;
    }

    @GetMapping
    public String getRegisterForm(Model model) {
        model.addAttribute("registrationUserDTO", new UserDTO());
        return "registrationForm";
    }

    @PostMapping
    public String registrationProcess(@ModelAttribute UserDTO registrationUserDTO, Model model) {
        System.out.println(registrationUserDTO.getEmail());
        User user = registrationPersistenceService.registerNewUserAccount(registrationUserDTO);
        model.addAttribute("user", user);
        return "successfulRegistration";
    }
}
