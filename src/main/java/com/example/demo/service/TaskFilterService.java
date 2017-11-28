package com.example.demo.service;

import com.example.demo.model.Task;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskFilterService {


    public List<Task> filterUnfinishedTasks(List<Task> all) {
        return all
                .stream()
                .filter(task -> !task.getDone())
                .collect(Collectors.toList());
    }

    public List<Task> filterUnfinishedTasksInOrderFromNearestEndDate(List<Task> all) {
        return all
                .stream()
                .filter(task -> !task.getDone())
                .sorted(Comparator.comparing(Task::getEndDate))
                .collect(
                        Collectors.toList());
    }
}
