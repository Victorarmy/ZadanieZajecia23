package com.example.demo.controller;

import com.example.demo.model.Options;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
class HomeController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("options", Options.values());
        return "index";
    }
}
