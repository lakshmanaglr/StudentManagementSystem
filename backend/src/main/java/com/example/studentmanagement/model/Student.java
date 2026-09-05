package com.example.studentmanagement.model;

public class Student {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String course;
    private Integer year;

    public Student() {}
    public Student(Integer id, String name, String email, String phone, String course, Integer year) {
        this.id=id; this.name=name; this.email=email; this.phone=phone; this.course=course; this.year=year;
    }
    public Integer getId(){return id;} public void setId(Integer id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public String getPhone(){return phone;} public void setPhone(String phone){this.phone=phone;}
    public String getCourse(){return course;} public void setCourse(String course){this.course=course;}
    public Integer getYear(){return year;} public void setYear(Integer year){this.year=year;}
}
