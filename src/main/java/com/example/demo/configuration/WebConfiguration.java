package com.example.demo.configuration;

import com.example.demo.converter.TaskConverter;
import com.example.demo.dao.TaskPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfiguration  extends WebMvcConfigurerAdapter{

    @Autowired
    private TaskPersistenceService taskPersistenceService;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new TaskConverter(taskPersistenceService));
    }
}
