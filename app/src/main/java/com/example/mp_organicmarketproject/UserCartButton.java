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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mp_organicmarketproject.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserCartButton extends Fragment {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    RecyclerView cartRecyclerView;
    List<AddedProductInCart> addedProductsInCart;
    CartAdapter userCartAdapter;
    FirebaseUser user;
    TextView totalPriceOfProducts;
    SwipeRefreshLayout swipeRefreshLayout;

    public UserCartButton(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        firebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
        databaseReference =  FirebaseDatabase.getInstance().getReference();
        user = firebaseAuth.getCurrentUser();
        View v = inflater.inflate(R.layout.usercart_fragment, container, false);
        totalPriceOfProducts = v.findViewById(R.id.TotalPriceOfProducts);
        swipeRefreshLayout = v.findViewById(R.id.cartRefresh);
        gettingUserInfo(v);

        showAddedProductsToCart(v);
        return v;

    }



    public void showAddedProductsToCart(View v){

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showAddedProductsToCart(v);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        cartRecyclerView= v.findViewById(R.id.userCartRecyclerView);
        cartRecyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),1);
        cartRecyclerView.setLayoutManager(mLayoutManager);
        addedProductsInCart = new ArrayList<>();
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User Cart").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()){
                    if(snapshot.exists()){
                        AddedProductInCart addedProduct = postSnapshot.getValue(AddedProductInCart.class);
                        addedProductsInCart.add(addedProduct);
                    }

                }

                userCartAdapter = new CartAdapter(getContext(), addedProductsInCart, v);
                cartRecyclerView.setAdapter(userCartAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_LONG);

            }
        });

    }

    public void gettingUserInfo(View v){
        databaseReference.child("users").child(firebaseAuth.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            User user = task.getResult().getValue(User.class);
                            TextView textViewUser = (TextView) v.findViewById(R.id.cartUserInfo);
                            textViewUser.setText( user.name + " " + user.surname );
                        }
                        else{

                            System.out.println(task.getException().getMessage());

                        }
                    }
                });

    }
}
