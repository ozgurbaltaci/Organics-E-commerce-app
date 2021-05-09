package com.example.mp_organicmarketproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mp_organicmarketproject.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserFavoritesButton extends Fragment {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public UserFavoritesButton(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference =  FirebaseDatabase.getInstance().getReference();
        View v = inflater.inflate(R.layout.userfavorites_fragment, container, false);

        introFavorite(v);
        return v;


    }


    public void introFavorite(View v){

        databaseReference.child("users").child(firebaseAuth.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            User user = task.getResult().getValue(User.class);
                            TextView textViewUser = (TextView) v.findViewById(R.id.favoriteInfoStart);
                            textViewUser.setText("Your favorite list, "+ user.name + " " + user.surname );
                        }
                        else{

                            System.out.println(task.getException().getMessage());

                        }
                    }
                });




    }
}
