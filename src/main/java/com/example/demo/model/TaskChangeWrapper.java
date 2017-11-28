package com.example.demo.model;

import java.util.List;

public class TaskChangeWrapper {

    private List<Task> taskList;

    public TaskChangeWrapper() {
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
