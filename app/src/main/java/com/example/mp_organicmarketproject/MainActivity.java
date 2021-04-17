package com.example.mp_organicmarketproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * @since 05/04/2021
 * @author Ozgur Baltaci
 * @author Sevinc Ekin
 * Main Activity
 * Note - It will be update
 * homepage-2
 */
public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ViewPager viewPager2;
    CircleIndicator circleSlider;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        circleSlider = findViewById(R.id.circleSlider);

        tabLayout = findViewById(R.id.bottomBar);
        viewPager2 = findViewById(R.id.viewPager2);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.homePagetext));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.searchText));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.favoritesText));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.cartText));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.profileText));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager2.setAdapter(adapter);
        viewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        List<Integer> imageList = new ArrayList<>();

        imageList.add(R.drawable.image2);
        imageList.add(R.drawable.image6);
        imageList.add(R.drawable.image3);
        imageList.add(R.drawable.image4);
        imageList.add(R.drawable.image5);
        imageList.add(R.drawable.image1);



        AdapterHome adapterHome = new AdapterHome(imageList);
        viewPager.setAdapter(adapterHome);
        circleSlider.setViewPager(viewPager);

        GridView gridView = findViewById(R.id.userHomePageGrid);
        gridView.setAdapter(new CategoriesAdapter(this));
    }



}