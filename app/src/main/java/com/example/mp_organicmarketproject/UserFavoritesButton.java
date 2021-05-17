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

public class UserFavoritesButton extends Fragment {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    RecyclerView favoritesRecyclerView;
    List<UserFavorite> userFavoriteList;
    UserFavoriteAdapter favoritesAdapter;
    FirebaseUser user;

    public UserFavoritesButton(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference =  FirebaseDatabase.getInstance().getReference();
        user = firebaseAuth.getCurrentUser();
        View v = inflater.inflate(R.layout.userfavorites_fragment, container, false);

        gettingUserInfo(v);
        showFavorites(v);
        return v;

    }


    public void showFavorites(View v){
        favoritesRecyclerView= v.findViewById(R.id.favoritesRecyclerView);
        favoritesRecyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),2);
        favoritesRecyclerView.setLayoutManager(mLayoutManager);
        userFavoriteList = new ArrayList<>();
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User Favorites").child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()){
                    UserFavorite favoriteProduct = postSnapshot.getValue(UserFavorite.class);
                    userFavoriteList.add(favoriteProduct);
                }
                favoritesAdapter = new UserFavoriteAdapter(getContext(),userFavoriteList);
                favoritesRecyclerView.setAdapter(favoritesAdapter);
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
                            TextView textViewUser = (TextView) v.findViewById(R.id.favoritesUserInfo);
                            textViewUser.setText( user.name + " " + user.surname );
                        }
                        else{

                            System.out.println(task.getException().getMessage());

                        }
                    }
                });

    }
}
