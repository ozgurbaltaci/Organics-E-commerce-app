package com.example.mp_organicmarketproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * @since 05/04/2021
 * @author Sevinc Ekin
 * @author Ozgur Baltaci
 * Forgot Password Activity
 */

public class ForgotPasswordActivity extends AppCompatActivity {
    private Button resetPasswordButton;
    private EditText emailEditText;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        resetPasswordButton = (Button) findViewById(R.id.resetPassword);
        emailEditText = (EditText) findViewById(R.id.forgotPassword_mail);
        auth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    public void backToLogin(View view){
        Intent backToLogin = new Intent (this,LoginActivity.class);
        startActivity(backToLogin);

    }




    private void resetPassword(){
        String email = emailEditText.getText().toString().trim();
        if(email.isEmpty()) {
            emailEditText.setError("Email is required!");
            emailEditText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please enter a valid e-mail!");
            emailEditText.requestFocus();
            return;
        }
        setProgressBarVisibility(true);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Toast.makeText(ForgotPasswordActivity.this,"Check your email to reset your password !",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ForgotPasswordActivity.this,"Something wrong happened.Please try again !",Toast.LENGTH_LONG).show();
            }
        });

    }
}