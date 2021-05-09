package com.example.mp_organicmarketproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class UserHomePageButton extends Fragment {
    ViewPager viewPager;
    ViewPager viewPager2;
    CircleIndicator circleSlider;
    TabLayout tabLayout;
    RecyclerView recyclerView;
    List<Integer> imageList;
    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> cCategories;

    RecyclerView.LayoutManager recyclerManager;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();




    public UserHomePageButton(){


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        imageList = new ArrayList<>();

        View v = inflater.inflate(R.layout.userhomepage_fragment, container, false);

        recyclerView = v.findViewById(R.id.recylePencere);
        recyclerManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(recyclerManager);

        //circleSlider = v.findViewById(R.id.circleSlider);
        readDatabase();
        showCategories(v);


//        GridView gridView = v.findViewById(R.id.userHomePageGrid);
//        gridView.setAdapter(new CategoriesAdapter(this.getContext()));
        return v;

    }

    private void readDatabase() {

        List<Slider> imageList = new ArrayList<>();

        DatabaseReference myRef = firebaseDatabase.getReference("Slider");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot data: snapshot.getChildren()) {

                    imageList.add(new Slider(data.child("title").getValue().toString(), data.child("image").getValue().toString()));
                    System.out.println(imageList.get(0).image);
                    System.out.println(imageList.get(0).title);
                    System.out.println(imageList.size()+"----------------------------------------");

                }


                AdapterHome adapterHome = new AdapterHome(imageList);
                recyclerView.setAdapter(adapterHome);

                //circleSlider.setViewPager(viewPager);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    public void showCategories(View v){
        categoryRecyclerView= v.findViewById(R.id.category_view);
        categoryRecyclerView.setNestedScrollingEnabled(false);
        categoryRecyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getContext(),3);
        categoryRecyclerView.setLayoutManager(mLayoutManager);
        cCategories = new ArrayList<>();
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("Categories");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()){
                    Category category = postSnapshot.getValue(Category.class);
                    cCategories.add(category);
                }
                categoryAdapter = new CategoryAdapter(getContext(),cCategories);
                categoryRecyclerView.setAdapter(categoryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_LONG);

            }
        });
    }

    }





/*





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

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        FirebaseDatabase.getInstance().getReference().child("Slider")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot data:dataSnapshot.getChildren()){
                            imageList.add(new Integer(data.child("image").getValue().toString(), data.child("title").getValue().toString(),ScaleTypes.FIT));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

*/
        /*
        imageList.add(R.drawable.image2);
        imageList.add(R.drawable.image6);
        imageList.add(R.drawable.image3);
        imageList.add(R.drawable.image4);
        imageList.add(R.drawable.image5);
        imageList.add(R.drawable.image1);
*/
    /*
AdapterHome adapterHome = new AdapterHome(imageList);
        viewPager.setAdapter(adapterHome);
                circleSlider.setViewPager(viewPager);

                GridView gridView = v.findViewById(R.id.userHomePageGrid);
                gridView.setAdapter(new CategoriesAdapter(this.getContext()));
                return v;
                }
                }









 */




































/*
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

import me.relex.circleindicator.CircleIndicator;*//*

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


*/
