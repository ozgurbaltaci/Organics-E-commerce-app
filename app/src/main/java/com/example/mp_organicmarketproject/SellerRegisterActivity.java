package com.example.mp_organicmarketproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
/**
 * @since 05/04/2021
 * @author Ozgur Baltaci
 * @author Sevinc Ekin
 * hede
 * Seller Register Activity
 */

public class SellerRegisterActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_register);
        myAuth = FirebaseAuth.getInstance();
    }
    public void createSeller(View view) {
        EditText editText = (EditText) findViewById(R.id.sellerSignUp_SellerTitle);
        String sellerTitle = editText.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.sellerSignUp_SellerMail);
        String sellerMail = editText2.getText().toString().trim();
        EditText editText3 = findViewById(R.id.sellerSignUp_SellerPhone);
        String sellerPhone = editText3.getText().toString();
        EditText editText4 = findViewById(R.id.sellerSignUp_SellerAdress);
        String sellerAdress = editText4.getText().toString();
        EditText editText5 = findViewById(R.id.sellerSignUp_SellerPassword);
        String sellerPassword = editText5.getText().toString();
        EditText editText6 = findViewById(R.id.sellerSignUp_SellerConfirmedPassword);
        String sellerConfirmedPassword = editText6.getText().toString();

        CheckBox checkBox = (CheckBox) findViewById(R.id.SellerSignUp_checkBox);


        if(sellerTitle.length()<2 || sellerAdress.length()<2 || sellerPhone.length()<1){
            Toast.makeText(SellerRegisterActivity.this,"Please make sure you fill blanks correctly", Toast.LENGTH_SHORT).show();
            return;
        }




        if (!checkBox.isChecked()){
            Toast.makeText(SellerRegisterActivity.this,"You must accept the terms of services", Toast.LENGTH_SHORT).show();
            return;
        }



        if (!sellerPassword.equals(sellerConfirmedPassword)) {
            Toast.makeText(SellerRegisterActivity.this, "Password and Confirm Password doesn't match.", Toast.LENGTH_SHORT).show();
        } else {
            myAuth.createUserWithEmailAndPassword(sellerMail, sellerPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser seller = myAuth.getCurrentUser();
                    } else
                        Toast.makeText(SellerRegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}