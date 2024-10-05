package com.todoexample.demo.controller.impl;

import com.todoexample.demo.controller.IUserController;
import com.todoexample.demo.dto.DtoUser;
import com.todoexample.demo.entity.User;
import com.todoexample.demo.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/user")
public class UserControllerImpl implements IUserController {
    @Autowired
    private IUserService userService;

    @PostMapping(path = "/save")
    @Override
    public DtoUser saveUser(@RequestBody @Valid DtoUser dtoUser) {
        return userService.saveUser(dtoUser);
    }

    @GetMapping("/list")
    @Override
    public List<DtoUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/list/{userId}")
    @Override
    public DtoUser getUserById(@PathVariable(name = "userId") Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/delete/{userId}")
    @Override
    public void deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "/update/{userId}")
    @Override
    public DtoUser updateUser(@PathVariable(name = "userId") Long userId,@RequestBody @Valid DtoUser dtoUser) {
        return userService.updateUser(userId,dtoUser);
    }
}
