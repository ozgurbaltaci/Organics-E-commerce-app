package com.example.mp_organicmarketproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mp_organicmarketproject.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    FirebaseUser user;
    TextView textViewUser, textViewUserSurname, phoneNumber;



    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String USERS = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_my_profile_layout);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference =  FirebaseDatabase.getInstance().getReference();
        user = firebaseAuth.getCurrentUser();


        textViewUser = findViewById(R.id.userNameSurname);
        textViewUserSurname =  findViewById(R.id.userSurname2);
        phoneNumber =  findViewById(R.id.phoneNumberUser);


        gettingUserInfo();
    }


    public void gettingUserInfo(){


        TextView emailAddressUser = (TextView) findViewById(R.id.emailAddressTitle);
        emailAddressUser.setText(firebaseAuth.getCurrentUser().getEmail());

        emailAddressUser.setFocusable(false);

        databaseReference.child("users").child(firebaseAuth.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            User user = task.getResult().getValue(User.class);

                            textViewUser.setText( user.name );
                            textViewUserSurname.setText( user.surname );
                            phoneNumber.setText( user.phone  );
                        }
                        else{

                            System.out.println(task.getException().getMessage());

                        }
                    }
                });

    }





}