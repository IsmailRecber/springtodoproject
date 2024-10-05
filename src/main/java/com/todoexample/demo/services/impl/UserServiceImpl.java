package com.todoexample.demo.services.impl;

import com.todoexample.demo.dto.DtoTask;
import com.todoexample.demo.dto.DtoUser;
import com.todoexample.demo.entity.Task;
import com.todoexample.demo.entity.User;
import com.todoexample.demo.repositories.UserRepository;
import com.todoexample.demo.services.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public DtoUser saveUser(DtoUser dtoUser) {
        User user=new User();
        DtoUser response=new DtoUser();
        BeanUtils.copyProperties(dtoUser,user);
        User dbUser=userRepository.save(user);
        BeanUtils.copyProperties(dbUser,response);
        return response;
    }

    @Override
    public List<DtoUser> getAllUsers() {
        List<DtoUser> dtoUsers = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            DtoUser dtoUser = new DtoUser();

            // Manuel olarak Task listesini doldur
            List<DtoTask> dtoTasks = new ArrayList<>();
            for (Task task : user.getTasks()) {
                DtoTask dtoTask = new DtoTask();
                dtoTask.setId(task.getId());
                dtoTask.setUserId(user.getId());
                dtoTask.setTitle(task.getTitle());
                dtoTask.setDescription(task.getDescription());
                dtoTasks.add(dtoTask);
            }

            dtoUser.setTasks(dtoTasks);



            dtoUser.setId(user.getId());
            dtoUser.setUserName(user.getUserName());

            dtoUsers.add(dtoUser);
        }

        return dtoUsers;
    }

    @Override
    public DtoUser getUserById(Long userId) {
        Optional<User> optional=userRepository.findById(userId);
        User user=optional.get();

        if (optional.isEmpty()){
            return null;
        }

        DtoUser response=new DtoUser();
        BeanUtils.copyProperties(user,response);

        for (Task task:user.getTasks()){
            DtoTask dtoTask=new DtoTask();
            BeanUtils.copyProperties(task,dtoTask);
            response.getTasks().add(dtoTask);

        }


        return response;
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> optional=userRepository.findById(userId);
        if (optional.isPresent()){
            userRepository.delete(optional.get());
        }
    }

    @Override
    public DtoUser updateUser(Long userId, DtoUser dtoUser) {
        DtoUser response=new DtoUser();
        Optional<User> optional=userRepository.findById(userId);

        if (optional.isPresent()){
            User dbUser=optional.get();


            dbUser.setUserName(dtoUser.getUserName());
            List<Task> tasks=new ArrayList<>();
            for (DtoTask task: dtoUser.getTasks()){
                Task dbtask=new Task();
                dbtask.setUser(dbUser);
                dbtask.setDescription(task.getDescription());
                dbtask.setTitle(task.getTitle());
                BeanUtils.copyProperties(task,dbtask);
                tasks.add(dbtask);
            }
            dbUser.setTasks(tasks);

            User updatedUser=userRepository.save(dbUser);
            BeanUtils.copyProperties(updatedUser,response);

            return response;
        }


        return null;
    }
}
