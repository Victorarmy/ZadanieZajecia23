package com.example.demo.controller;

import com.example.demo.dao.CategoryPersistenceService;
import com.example.demo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryPersistenceService categoryPersistenceService;

    @Autowired
    public CategoryController(CategoryPersistenceService categoryPersistenceService) {
        this.categoryPersistenceService = categoryPersistenceService;
    }

    @GetMapping("/add")
    public String getCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addCategoryForm";
    }

    @PostMapping
    public String addCategory(@ModelAttribute Category category, Model model) {
        categoryPersistenceService.save(category);
        return "savedPage";
    }
}
