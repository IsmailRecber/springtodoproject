package com.todoexample.demo.controller.impl;


import com.todoexample.demo.controller.ITaskController;
import com.todoexample.demo.dto.DtoTask;
import com.todoexample.demo.services.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/task")
public class TaskControllerImpl implements ITaskController {
    @Autowired
    private ITaskService taskService;

    @PostMapping("/save")
    @Override
    public DtoTask saveTask(@RequestBody @Valid DtoTask dtoTask) {
        return taskService.saveTask(dtoTask);
    }

    @DeleteMapping("/delete/{taskId}")
    @Override
    public void deleteTask(@PathVariable(name = "taskId") Long taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping("/update/{taskId}")
    @Override
    public DtoTask updateTask(@PathVariable(name = "taskId") Long taskId,@RequestBody @Valid DtoTask dtoTask) {
        return taskService.updateTask(taskId,dtoTask);
    }

    @Override
    public DtoTask getTaskById(Long taskId) {
        return null;
    }

    @GetMapping("/list")
    @Override
    public List<DtoTask> getAllTasks() {
        return taskService.getAllTasks();
    }
}
