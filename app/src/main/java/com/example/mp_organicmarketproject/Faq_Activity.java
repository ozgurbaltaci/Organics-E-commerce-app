package com.example.mp_organicmarketproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Faq_Activity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    RecyclerView faqRecyclerView;
    List<FAQ> faqList;
    FaqAdapter faqAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frequently_asked_questions);

        databaseReference =  FirebaseDatabase.getInstance().getReference();
        showFAQ();
    }

    public void showFAQ(){
        faqRecyclerView= findViewById(R.id.faqrecylerview);
        faqRecyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        faqRecyclerView.setLayoutManager(mLayoutManager);
        faqList = new ArrayList<>();
        DatabaseReference FaqReference;
        FaqReference = FirebaseDatabase.getInstance().getReference().child("FAQ");
        FaqReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()){
                    FAQ faq = postSnapshot.getValue(FAQ.class);
                    faqList.add(faq);
                }
                FaqAdapter faqAdapter= new FaqAdapter(getApplicationContext(),faqList);
                faqRecyclerView.setAdapter(faqAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG);

            }
        });
    }
}