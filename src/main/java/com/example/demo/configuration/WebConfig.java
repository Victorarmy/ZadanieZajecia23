package com.example.demo.configuration;

import com.example.demo.converter.TaskConverter;
import com.example.demo.dao.TaskPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    @Autowired
    private TaskPersistenceService taskPersistenceService;

    @Bean
    public TaskConverter taskConverter() {
        return new TaskConverter(taskPersistenceService);
    }
}
