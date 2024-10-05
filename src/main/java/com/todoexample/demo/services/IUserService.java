package com.todoexample.demo.services;

import com.todoexample.demo.dto.DtoTask;
import com.todoexample.demo.dto.DtoUser;
import com.todoexample.demo.entity.User;

import java.util.List;

public interface IUserService {

    public DtoUser saveUser(DtoUser dtoUser);

    public List<DtoUser> getAllUsers();

    public DtoUser getUserById(Long userId);

    public void deleteUser(Long userId);

    public DtoUser updateUser(Long userId,DtoUser dtoUser);
}
