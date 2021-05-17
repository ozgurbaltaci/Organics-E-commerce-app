package com.example.mp_organicmarketproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

     RecyclerView productsRecyclerView;
     RecyclerView.LayoutManager recyclerProductManager;
     TextView productCategoryTitle;
     String currentCategory;

    private List<Product> products;

    private ProductsAdapter productsAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.product_fragment_layout, container, false);
        currentCategory = getActivity().getIntent().getStringExtra("Category");
        System.out.println(currentCategory + " ye tıklandı.");

        productCategoryTitle = v.findViewById(R.id.ProductCategoryTitle);
        productCategoryTitle.setText(currentCategory);

        showProducts(v);
        
        return v;

    }

    public void showProducts(View v){
        productsRecyclerView= v.findViewById(R.id.product_recyclerView);
        productsRecyclerView.setNestedScrollingEnabled(false);
        productsRecyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
        productsRecyclerView.setLayoutManager(mLayoutManager);
        products = new ArrayList<>();
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("Category:" + currentCategory);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()){
                    Product product = postSnapshot.getValue(Product.class);
                    products.add(product);
                }
                productsAdapter = new ProductsAdapter(getContext(),products);
                productsRecyclerView.setAdapter(productsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_LONG);

            }
        });
    }
}
