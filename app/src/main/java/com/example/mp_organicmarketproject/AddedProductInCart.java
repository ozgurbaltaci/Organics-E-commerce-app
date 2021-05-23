package com.example.mp_organicmarketproject;

public class AddedProductInCart {
    private String productName, productPhoto,productPrice;
    int desiredAmount;

    public AddedProductInCart(){

    }
    public AddedProductInCart(String productName, String productPhoto, String productPrice,int desiredAmount){
        this.productName = productName;
        this.productPhoto = productPhoto;
        this.productPrice = productPrice;
        this.desiredAmount = desiredAmount;

    }

    public String getproductName() {
        return productName;
    }

    public void setproductName(String productName) {
        this.productName = productName;
    }

    public int getDesiredAmount() {
        return desiredAmount;
    }

    public void setDesiredAmount(int desiredAmount) {
        this.desiredAmount = desiredAmount;
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
