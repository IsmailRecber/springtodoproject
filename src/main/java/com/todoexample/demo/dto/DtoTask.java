package com.todoexample.demo.dto;


import com.todoexample.demo.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoTask {

    private Long id;

    private String title;

    private String description;

    private Long userId;
}

