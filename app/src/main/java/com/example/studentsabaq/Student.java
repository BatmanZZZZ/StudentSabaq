package com.example.studentsabaq;

public class Student {
    private int id;



    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private int image;
    private int sabaq_start;

    public Student(int id, int image, int sabaq_start, int sabaq_end, int sabqi, int manzil_range, String name) {
        this.id = id;
        this.image = image;
        this.sabaq_start = sabaq_start;
        this.sabaq_end = sabaq_end;
        this.sabqi = sabqi;
        this.manzil_range = manzil_range;
        this.name = name;
    }

    private int sabaq_end;
    private int sabqi;
    private int manzil_range;

    private String name;

    public int getSabaq_start() {
        return sabaq_start;
    }

    public int getSabaq_end() {
        return sabaq_end;
    }

    public int getSabqi() {
        return sabqi;
    }

    public int getManzil_range() {
        return manzil_range;
    }



    public void setId(int id) {
        this.id = id;
    }



    public void setSabaq_start(int sabaq_start) {
        this.sabaq_start = sabaq_start;
    }

    public void setSabaq_end(int sabaq_end) {
        this.sabaq_end = sabaq_end;
    }

    public void setSabqi(int sabqi) {
        this.sabqi = sabqi;
    }

    public void setManzil_range(int manzil_range) {
        this.manzil_range = manzil_range;
    }

    public void setName(String name) {
        this.name = name;
    }





    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }
}


