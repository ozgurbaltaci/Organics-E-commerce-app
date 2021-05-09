package com.example.mp_organicmarketproject.dto;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Seller {

    public String title;
    public String phone;
    public String address;

    public Seller() {

    }

    public Seller(String title, String phone, String address) {
        this.title = title;
        this.phone = phone;
        this.address = address;
    }
}
