package com.todoexample.demo.services.impl;

import com.todoexample.demo.dto.DtoTask;
import com.todoexample.demo.entity.Task;
import com.todoexample.demo.entity.User;
import com.todoexample.demo.repositories.TaskRepository;
import com.todoexample.demo.repositories.UserRepository;
import com.todoexample.demo.services.ITaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements ITaskService {
    @Autowired
    private  TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public DtoTask saveTask(DtoTask dtoTask) {
        Optional<User> optional=userRepository.findById(dtoTask.getUserId());
        User user=optional.get();

        Task task=new Task();
        BeanUtils.copyProperties(dtoTask,task);
        task.setUser(user);


        Task savedTask =taskRepository.save(task);
        DtoTask response=new DtoTask();
        BeanUtils.copyProperties(savedTask,response);


        return response;
    }

    @Override
    public void deleteTask(Long taskId) {
        Optional<Task>optional=taskRepository.findById(taskId);
        if (optional.isPresent()){
            taskRepository.delete(optional.get());
        }
    }

    @Override
    public DtoTask updateTask(Long taskId, DtoTask dtoTask) {
        Optional<Task> optional=taskRepository.findById(taskId);
        Task task=optional.get();

        task.setTitle(dtoTask.getTitle());
        task.setDescription(dtoTask.getDescription());

        Task dbTask=taskRepository.save(task);
        DtoTask response=new DtoTask();

        response.setUserId(dbTask.getUser().getId());
        response.setTitle(dbTask.getTitle());
        response.setDescription(dbTask.getDescription());
        response.setId(dbTask.getId());
        return response;
    }

    @Override
    public DtoTask getTaskById(Long taskId) {
        return null;
    }

    @Override
    public List<DtoTask> getAllTasks() {
        List<Task> tasks=taskRepository.findAll();
        List<DtoTask> response=new ArrayList<>();
        for (Task task:tasks){
            DtoTask dtoTask=new DtoTask();
            BeanUtils.copyProperties(task,dtoTask);
            dtoTask.setUserId(task.getUser().getId());
            response.add(dtoTask);

        }

        return response;
    }
}
