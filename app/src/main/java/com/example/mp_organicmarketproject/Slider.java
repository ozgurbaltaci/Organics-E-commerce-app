package com.example.mp_organicmarketproject;

public class Slider {
    String image;
    String title;

    public Slider( String title, String image) {
        this.image = image;
        this.title = title;
    }

    public Slider() {
    }

    public String getImageURL() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
