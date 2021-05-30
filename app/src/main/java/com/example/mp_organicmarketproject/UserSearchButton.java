package com.example.mp_organicmarketproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.Toast;

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

        ProductsAdapter adapterProducts;
        RadioGroup group;
         RadioGroup group2;
        FirebaseDatabase database;
        DatabaseReference databaseReference;
        List<Product> productList;
        RecyclerView.LayoutManager layoutManager;
        List<String> categories;
        ProductsAdapter productsAdapter;
        RecyclerView searchRecyclerView;
        SearchView searchView;

    public UserSearchButton(){


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usersearch_fragment, container, false);
        searchView = view.findViewById(R.id.searchViewOnSearch);
        showingProducts(view);


        return view;
    }

    public void showingProducts(View view){
        searchRecyclerView= view.findViewById(R.id.listOfAllCategories);
        searchRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getContext(),2);
        searchRecyclerView.setLayoutManager(mLayoutManager);
        productList = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("Categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    String name = child.getKey();

                    FirebaseDatabase.getInstance().getReference().child("Category:" + name).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                Product product = postSnapshot.getValue(Product.class);
                                productList.add(product);

                            }
                            productsAdapter = new ProductsAdapter(getContext(), productList);
                            searchRecyclerView.setAdapter(productsAdapter);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_LONG);

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if(searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }

    }
    private void search(String newText){
        ArrayList<Product> searchedProducts = new ArrayList<>();
        for(Product products : productList){
            if(products.getproductName().toLowerCase().contains(newText.toLowerCase())){
                searchedProducts.add(products);
            }
        }
        ProductsAdapter productsAdapter2 = new ProductsAdapter(getContext(),searchedProducts);
        searchRecyclerView.setAdapter(productsAdapter2);

    }
}

//cardView = view.findViewById(R.id.listOfAllCategories);
//        databaseReference= FirebaseDatabase.getInstance().getReference();
//
//        group = view.findViewById(R.id.radioButtonGroup);
//        group2 = view.findViewById(R.id.radioButtonGroup2);
//
//
//        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//@Override
//public void onCheckedChanged(RadioGroup group, int checkedId) {
//        int id = group.getCheckedRadioButtonId();
//
//        RadioButton radioButtonCurrent = view.findViewById(id);
//        String currentCategory = "Category:"+radioButtonCurrent.getText().toString();
//        buttonGroupDatabase(currentCategory);
//
//        }
//        });
//
//
//        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//@Override
//public void onCheckedChanged(RadioGroup group, int checkedId) {
//        int id = group.getCheckedRadioButtonId();
//
//        RadioButton radioButtonCurrent = view.findViewById(id);
//        String currentCategory = "Category:"+radioButtonCurrent.getText().toString();
//        buttonGroupDatabase(currentCategory);
//
//        }
//        });
//
//
//        layoutManager = new GridLayoutManager(getContext(),2);
//        //layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//        cardView.setLayoutManager(layoutManager);
//        return view;
//        }




//public void buttonGroupDatabase(String category){
//        productList= new ArrayList<>();
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//        for (DataSnapshot ds: snapshot.getChildren()) {
//        if(ds.getKey().equalsIgnoreCase(category)){
//        for (DataSnapshot innerds:ds.getChildren()
//        ) {
//        productList.add(innerds.getValue(Product.class));
//        for (Product a:productList
//        ) {
//        System.out.println(a.getproductName()+"*****************");
//        }
//        }
//
//        }
//
//        }
//        adapterProducts = new ProductsAdapter(getContext(),productList);
//        System.out.println(productList.size());
//        cardView.setAdapter(adapterProducts);
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//        });
//
//
//        }

