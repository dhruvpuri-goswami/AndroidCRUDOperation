package com.example.androidcrudoperation.modal;

public class Courses {
    private int id;
    private String name;
    private String duration;
    private String description;

    public Courses(String name, String duration, String description){
        this.name=name;
        this.duration=duration;
        this.description=description;
    }

    public Courses(int id, String name, String duration, String description){
        this.id=id;
        this.name=name;
        this.duration=duration;
        this.description=description;
    }

    public Courses() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
