package com.example.mp_organicmarketproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

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
 */
public class MainActivity extends AppCompatActivity {


    private FirebaseAuth myAuth;

    ViewPager viewPager;
    CircleIndicator circleSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        circleSlider = findViewById(R.id.circleSlider);
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