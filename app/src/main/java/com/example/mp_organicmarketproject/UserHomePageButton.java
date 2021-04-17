package com.example.mp_organicmarketproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class UserHomePageButton extends Fragment {
    ViewPager viewPager;
    ViewPager viewPager2;
    CircleIndicator circleSlider;
    TabLayout tabLayout;


    public UserHomePageButton(){


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.userhomepage_fragment, container, false);

        viewPager = v.findViewById(R.id.viewPager);
        circleSlider = v.findViewById(R.id.circleSlider);

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

        GridView gridView = v.findViewById(R.id.userHomePageGrid);
        gridView.setAdapter(new CategoriesAdapter(this.getContext()));
        return v;
    }
}
