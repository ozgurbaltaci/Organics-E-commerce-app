package com.example.mp_organicmarketproject;

public class UserFavorite {
    private String productName, productPhoto,productPrice;
    public UserFavorite(){

    }
    public UserFavorite(String productName, String productPhoto, String productPrice){
        this.productName = productName;
        this.productPhoto = productPhoto;
        this.productPrice = productPrice;
    }

    public String getproductName() {
        return productName;
    }

    public void setproductName(String productName) {
        this.productName = productName;
    }


    public String getproductPrice() {
        return productPrice;
    }


    public String getproductPhoto() {
        return productPhoto;
    }

    public void setproductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }
}
