package com.example.mp_organicmarketproject;

import android.content.Context;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {


    Context context;
    int totalTabs;

    public MyAdapter(@NonNull FragmentManager fm) {

        super(fm);
    }
    public MyAdapter(Context c, FragmentManager fm, int totalTabs){
        super(fm);
        this.context = c;
        this.totalTabs = totalTabs;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new UserHomePageButton();
            case 1:
                return new UserSearchButton();
            case 2:
                return new UserFavoritesButton();
            case 3:
                return new UserCartButton();
            case 4:
                return new UserProfileButton();
            default:
                return null;


        }

    }

    @Override
    public int getCount() {
        return totalTabs;
    }

}
