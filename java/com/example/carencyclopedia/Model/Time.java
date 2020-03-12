package com.example.carencyclopedia.Model;

public class Time {
    private String id, year, image;

    public Time() {
    }

    public Time(String id, String year, String image) {
        this.id = id;
        this.year = year;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
