package com.example.demo.converter;

import com.example.demo.dao.TaskPersistenceService;
import com.example.demo.model.Task;
import org.springframework.core.convert.converter.Converter;

public class TaskConverter implements Converter<String, Task>{

    private TaskPersistenceService taskPersistenceService;

    public TaskConverter(TaskPersistenceService taskPersistenceService) {
        this.taskPersistenceService = taskPersistenceService;
    }

    @Override
    public Task convert(String id) {
        return taskPersistenceService.find(Long.valueOf(id));
    }
}
