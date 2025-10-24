package com.student.management.model;


import jakarta.persistence.*;

@Entity
@Table(name = "Students")
public class Student{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    private Long id;

    private String name;
    private String email;
    private int age;

    public Student(String name, String email, int age){
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public void setAge(int age){
        this.age = age;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public int getAge(){
        return age;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
}