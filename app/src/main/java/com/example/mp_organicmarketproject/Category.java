package com.example.mp_organicmarketproject;

public class Category {
    private String categoryName, categoryPhoto;
    public Category(){

    }
    public Category(String categoryName,String categoryPhoto){
        this.categoryName = categoryName;
        this.categoryPhoto = categoryPhoto;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryPhoto() {
        return categoryPhoto;
    }

    public void setCategoryPhoto(String categoryPhoto) {
        this.categoryPhoto = categoryPhoto;
    }
}
