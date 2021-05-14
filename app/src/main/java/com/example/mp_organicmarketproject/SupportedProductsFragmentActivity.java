package com.example.mp_organicmarketproject;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.fragment.app.FragmentActivity;

public class SupportedProductsFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.products_activity_layout);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new UserProfileButton()).commit();
        }

    }
}
