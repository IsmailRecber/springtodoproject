package com.todoexample.demo.controller;

import com.todoexample.demo.dto.DtoTask;

import java.util.List;

public interface ITaskController {
    public DtoTask saveTask(DtoTask dtoTask);

    public void deleteTask(Long taskId);

    public DtoTask updateTask(Long taskId,DtoTask dtoTask);

    public DtoTask getTaskById(Long taskId);

    public List<DtoTask> getAllTasks();

}
