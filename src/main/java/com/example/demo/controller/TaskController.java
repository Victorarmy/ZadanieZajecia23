package com.example.demo.controller;

import com.example.demo.dao.CategoryPersistenceService;
import com.example.demo.dao.TaskPersistenceService;
import com.example.demo.model.Task;
import com.example.demo.service.TaskFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private TaskPersistenceService taskPersistenceService;
    private CategoryPersistenceService categoryPersistenceService;
    private TaskFilterService taskFilterService;

    @Autowired
    public TaskController(TaskPersistenceService taskPersistenceService, CategoryPersistenceService categoryPersistenceService, TaskFilterService taskFilterService) {
        this.taskPersistenceService = taskPersistenceService;
        this.categoryPersistenceService = categoryPersistenceService;
        this.taskFilterService = taskFilterService;
    }

    @PostMapping("{id}")
    public String editTask(@PathVariable Long id,
                           @ModelAttribute Task task,
                           Model model) {

        taskPersistenceService.updateTask(task);
        Task changedTask = taskPersistenceService.find(id);
        model.addAttribute("task", changedTask);
        model.addAttribute("changed", "Task changed");
        model.addAttribute("categories", categoryPersistenceService.findAll());

        //TODO make it resolved by spring property source

        return "editForm";
    }

    @GetMapping("{id}")
    public String getInformationAboutCertainTask(@PathVariable(name = "id") Task task, Model model) {

//        Task task = taskPersistenceService.find(id);
        model.addAttribute("task", task);
        model.addAttribute("categories", categoryPersistenceService.findAll());
        return "editForm";
    }


    @GetMapping("/done")
    public String getAllDone(Model model) {
        List<Task> tasks = taskPersistenceService.findAllDone();
        model.addAttribute("tasks", tasks);
        return "showTasks";

        //TODO how to show checkboxex for read only if the parameter is set true
    }

    @PostMapping("/done")
    public String matchDone(@RequestParam List<Task> doneTasks, Model model) {

        //TODO Select all button

        List<Task> tasks = taskFilterService.filterUnfinishedTasks(taskPersistenceService.findAllUnfinished());
        model.addAttribute("tasks", tasks);
        return "showTasks";
    }

    @GetMapping("/undone")
    public String getAllRecords(Model model) {
        List<Task> tasks = taskFilterService.filterUnfinishedTasksInOrderFromNearestEndDate(taskPersistenceService.findAll());
        model.addAttribute("tasks", tasks);
        return "showTasks";
    }

    @GetMapping("/add")
    public String getAddForm(Model model) {
        model.addAttribute("categories", categoryPersistenceService.findAll());
        model.addAttribute("task", new Task());
        return "addTaskForm";
    }

    @PostMapping("/add")
    public String addNewTask(@ModelAttribute Task task) {
        taskPersistenceService.save(task);
        return "savedPage";
    }
}