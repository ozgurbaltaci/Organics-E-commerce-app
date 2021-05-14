package com.example.mp_organicmarketproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    ViewPager ProductsViewPager;

    TabLayout productTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_activity_layout);

        ProductsViewPager = findViewById(R.id.viewPagerProducts);

        productTabLayout = findViewById(R.id.productsBottomBar);

        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_home_24));
        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_search_24));
        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_favorite_24));
        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_shopping_cart_24));
        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_person_24));
        productTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ProductAdapterDeneme adapter = new ProductAdapterDeneme(this,getSupportFragmentManager(),productTabLayout.getTabCount());
        ProductsViewPager.setAdapter(adapter);
        ProductsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productTabLayout));

        productTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ProductsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}