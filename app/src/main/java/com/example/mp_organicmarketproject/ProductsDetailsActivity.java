package com.example.mp_organicmarketproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ProductsDetailsActivity extends AppCompatActivity {

    ViewPager ProductsViewPager;

    TabLayout productTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_details);


        ProductsViewPager = findViewById(R.id.viewPagerProductsDetails);

        productTabLayout = findViewById(R.id.productsDetailsBottomBar);


        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_home_24));
        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_search_24));
        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_favorite_24));
        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_shopping_cart_24));
        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_person_24));
        productTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ProductDetailsAdapterForTabs adapter = new ProductDetailsAdapterForTabs(this,getSupportFragmentManager(),productTabLayout.getTabCount());
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