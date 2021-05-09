package com.example.mp_organicmarketproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import com.example.mp_organicmarketproject.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * @since 04/04/2021
 * @author Ozgur Baltaci
 * @author Sevinc Ekin
 * Register Activity
 */



public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myAuth = FirebaseAuth.getInstance();
    }



    public void createSeller (View view){
        Intent goToSellerRegister = new Intent (this,SellerRegisterActivity.class);
        startActivity(goToSellerRegister);
    }

    public void goLoginFromRegister (View view){
        Intent goToLoginFromRegister = new Intent (this,LoginActivity.class);
        startActivity(goToLoginFromRegister);
    }


    public void createUser(View view){

        EditText editTextEmail = findViewById(R.id.memberSignUp_Email1);
        String email = editTextEmail.getText().toString().trim();

        EditText editTextPassword = findViewById(R.id.memberSignUp_password1);
        String password = editTextPassword.getText().toString();

        EditText editTextConfirmPassword = findViewById(R.id.memberSignUp_confirmedPassword1);
        String confirmedPassword = editTextConfirmPassword.getText().toString();

        EditText editText = (EditText) findViewById(R.id.memberSignUp_Name1);
        String name = editText.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.memberSignUp_Surname1);
        String surname = editText2.getText().toString();

        EditText editText4 = findViewById(R.id.memberSignUp_Phone1);
        String phoneNumber = editText4.getText().toString();


        CheckBox checkBox = (CheckBox) findViewById(R.id.memberSignUp_checkBox3);



        if(name.length()<2 || surname.length()<2 || phoneNumber.length()<1){
            Toast.makeText(RegisterActivity.this,"Please make sure you fill blanks correctly", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!checkBox.isChecked()){
            Toast.makeText(RegisterActivity.this,"You must accept the terms of services", Toast.LENGTH_SHORT).show();
            return;
        }


        User userInfo = new User(name,surname,phoneNumber);

        if(!password.equals(confirmedPassword)){
            Toast.makeText(RegisterActivity.this,"Password and Confirm Password doesn't match.", Toast.LENGTH_SHORT).show();
        }

        else{
            Intent mainIntent = new Intent(this, MainActivity.class);
            myAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()){
                                FirebaseUser user = myAuth.getCurrentUser();
                                databaseReference = FirebaseDatabase.getInstance().getReference();
                                databaseReference.child("users").child(user.getUid()).setValue(userInfo);

                                System.out.println(user.getEmail());

                                startActivity(mainIntent);

                            }
                            else
                                Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();


                        }
                    });
        }
    }





}