package com.todoexample.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {

    private Long id;
    private String userName;
    private List<DtoTask> tasks=new ArrayList<>();
}
