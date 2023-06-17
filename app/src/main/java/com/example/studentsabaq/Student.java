package com.example.studentsabaq;

public class Student {
    private int id;
    private byte[] image;
    private String name;

    public Student(int id, byte[] image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}


