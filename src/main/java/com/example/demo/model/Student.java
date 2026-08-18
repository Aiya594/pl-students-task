package com.example.demo.model;

import lombok.Getter;
@Getter
public class Student {
    private Long id;
    private String iin;
    private String username;
    private String email;
    private Long groupID;


    public Student(Long id,String iin,String username,String email,Long groupID){
        this.id=id;
        this.iin=iin;
        this.email=email;
        this.username=username;
        this.groupID=groupID;
    }
}
