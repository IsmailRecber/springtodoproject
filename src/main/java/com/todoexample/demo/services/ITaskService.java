package com.todoexample.demo.services;

import com.todoexample.demo.dto.DtoTask;

import java.util.List;

public interface ITaskService {

    public DtoTask saveTask(DtoTask dtoTask);

    public void deleteTask(Long taskId);

    public DtoTask updateTask(Long taskId,DtoTask dtoTask);

    public DtoTask getTaskById(Long taskId);

    public List<DtoTask> getAllTasks();
}
