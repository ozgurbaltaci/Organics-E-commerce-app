package com.example.mp_organicmarketproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private RecyclerView productsRecyclerView;
    private List<Product> products;
    private String currentCategory;
    private ProductsAdapter productsAdapter;
    private TextView productCategoryTitle;
    private TabLayout productTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_activity_layout);
        currentCategory = getIntent().getStringExtra("Category");
        System.out.println(currentCategory + " ye tıklandı.");
        productCategoryTitle = findViewById(R.id.ProductCategoryTitle);
        productCategoryTitle.setText(currentCategory);
        productTabLayout = findViewById(R.id.productsBottomBar);

        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_home_24));
        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_search_24));
        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_favorite_24));
        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_shopping_cart_24));
        productTabLayout.addTab(productTabLayout.newTab().setIcon(R.drawable.ic_baseline_person_24));


        showProducts();
    }

    public void showProducts(){
        productsRecyclerView= findViewById(R.id.product_recyclerView);
//        productsRecyclerView.setNestedScrollingEnabled(false);
        productsRecyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
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
                productsAdapter = new ProductsAdapter(getApplicationContext(),products);
                productsRecyclerView.setAdapter(productsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG);

            }
        });
    }
}