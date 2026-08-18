package com.example.demo.entity;

import lombok.Getter;

@Getter
public class StudentEntity {
    private Long studentID;
    private String username;
    private String email;
    private Long groupID;
    private String iin;


    public StudentEntity(Long id,String iin,String username,String email, Long groupID){
        this.studentID=id;
        this.iin=iin;
        this.email=email;
        this.username=username;
        this.groupID=groupID;
    }
}
