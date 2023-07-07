package com.example.example2_trainningday2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    private int id;
    private String name;
    private String email;
    private String department;
    private List<String> roles;


}
