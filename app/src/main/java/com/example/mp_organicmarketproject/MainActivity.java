package com.example.mp_organicmarketproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * @since 05/04/2021
 * @author Ozgur Baltaci
 * @author Sevinc Ekin
 * Main Activity
 * Note - It will be update
 */
public class MainActivity extends AppCompatActivity {


    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView textViewUser = (TextView) findViewById(R.id.main_TextViewUser);
        textViewUser.setText("Welcome to the Organic Market, "+ myAuth.getCurrentUser().getEmail());
    }

    public void logout(View view){
        myAuth.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}