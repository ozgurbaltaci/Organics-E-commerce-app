package com.example.mp_organicmarketproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserSearchButton extends Fragment {
        RecyclerView cardView ;
        ProductsAdapter adapterProducts;
        RadioGroup group;
         RadioGroup group2;
        FirebaseDatabase database;
        DatabaseReference databaseReference;
        List<Product> productList;

        RecyclerView.LayoutManager layoutManager;

    public UserSearchButton(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.usersearch_fragment, container, false);
            cardView = view.findViewById(R.id.listOfAllCategories);
        group = view.findViewById(R.id.radioButtonGroup);
        group2 = view.findViewById(R.id.radioButtonGroup2);


        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();

                RadioButton radioButtonCurrent = view.findViewById(id);
                String currentCategory = "Category:"+radioButtonCurrent.getText().toString();
                buttonGroupDatabase(currentCategory);

            }
        });


        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();

                RadioButton radioButtonCurrent = view.findViewById(id);
                String currentCategory = "Category:"+radioButtonCurrent.getText().toString();
                buttonGroupDatabase(currentCategory);

            }
        });


        layoutManager = new GridLayoutManager(getContext(),2);
        //layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        cardView.setLayoutManager(layoutManager);
        return view;
    }




    public void buttonGroupDatabase(String category){
        productList= new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    if(ds.getKey().equalsIgnoreCase(category)){
                        for (DataSnapshot innerds:ds.getChildren()
                             ) {
                            productList.add(innerds.getValue(Product.class));
                            for (Product a:productList
                                 ) {
                                System.out.println(a.getproductName()+"*****************");
                            }
                        }

                    }

                }
                adapterProducts = new ProductsAdapter(getContext(),productList);
                System.out.println(productList.size());
                cardView.setAdapter(adapterProducts);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
