package com.example.mp_organicmarketproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * @since 04/04/2021
 * @author Sevinc Ekin
 * @author Ozgur Baltaci
 * Login Activity
 */

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myAuth = FirebaseAuth.getInstance();
    }


    public void navigateUser(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void goToAdminLogin (View view) {
        Intent adminIntent = new Intent (this,AdminActivity.class);
        startActivity(adminIntent);
    }



    public void login(View view){
        EditText editTextEmail = (EditText) findViewById(R.id.memberLogin_Email);
        String email = editTextEmail.getText().toString().trim();


        EditText editTextPassword = (EditText) findViewById(R.id.memberLogin_password);
        String password = editTextPassword.getText().toString();


        if(email.equals("") || password.equals ("")) {
            Toast.makeText(LoginActivity.this, "Please fill the blanks.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent mainIntent = new Intent(this, MainActivity.class);

            myAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                startActivity(mainIntent);
                            }
                            else{
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(myAuth.getCurrentUser()!=null){
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);

        }
    }



    public void forgotPassword(View view){
        Intent forgotPassword = new Intent (this,ForgotPasswordActivity.class);
        startActivity(forgotPassword);
    }



}