package com.example.mp_organicmarketproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mp_organicmarketproject.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyProfilePageActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    FirebaseUser user;

    private TextView occupationTextView, nameTextView, workTextView;
    private TextView emailTextView, phoneTextView, videoTextView;
    private ImageView userImageView, emailImageView, phoneImageView;
    private String password, email;

    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String USERS = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_page);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference =  FirebaseDatabase.getInstance().getReference();
        user = firebaseAuth.getCurrentUser();

        gettingUserInfo();

/*
        Intent intent = getIntent();
         email = intent.getStringExtra("email");
         nameTextView = findViewById(R.id.username_textview1);
        emailTextView = findViewById(R.id.email_textview1);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USERS);

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
        */

    }


    public void gettingUserInfo(){
        TextView emailAddressUser = (TextView) findViewById(R.id.emailAddressTitle);
        emailAddressUser.setText(firebaseAuth.getCurrentUser().getEmail());
        databaseReference.child("users").child(firebaseAuth.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            User user = task.getResult().getValue(User.class);
                            TextView textViewUser = (TextView) findViewById(R.id.userNameSurname);
                            TextView textViewUserSurname = (TextView) findViewById(R.id.userSurname2);

                            TextView phoneNumber = (TextView) findViewById(R.id.phoneNumberUser);

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