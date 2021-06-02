package com.example.mp_organicmarketproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class ProductDetailsFragment extends Fragment {

     RecyclerView commentsRecyclerView;
     TextView productTitle;
     String currentProductName;
     Button leaveCommentButton;
     private FirebaseUser currUser;
     private FirebaseAuth firebaseAuth;
    String userInfo;
    EditText comment;

    private List<Comment> comments;

    private CommentAdapter commentsAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.product__details_fragment_layout, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        currUser = firebaseAuth.getCurrentUser();

        comment = v.findViewById(R.id.newComment);
        currentProductName = getActivity().getIntent().getStringExtra("Clicked product");

        leaveCommentButton = (Button) v.findViewById(R.id.leaveCommentButton);

        productTitle = v.findViewById(R.id.productDetailsTitle);
        productTitle.setText(currentProductName);
        FirebaseDatabase.getInstance().getReference().child("users").child(firebaseAuth.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            User user = task.getResult().getValue(User.class);
                            userInfo = user.email;
                        }
                        else{

                            System.out.println(task.getException().getMessage());

                        }
                    }
                });

        showComments(v);

        addingNewComment();


        return v;

    }

    public void showComments(View v){
        commentsRecyclerView= v.findViewById(R.id.comments_recyclerview);
        commentsRecyclerView.setNestedScrollingEnabled(false);
        commentsRecyclerView.setHasFixedSize(true);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(),1);
        commentsRecyclerView.setLayoutManager(mLayoutManager);
        comments = new ArrayList<>();
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference("Comments").child(currentProductName);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()){
                    Comment comment = postSnapshot.getValue(Comment.class);
                    comments.add(comment);
                }
                commentsAdapter = new CommentAdapter(getContext(),comments);
                commentsRecyclerView.setAdapter(commentsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(), Toast.LENGTH_LONG);

            }
        });
    }

    public void addingNewComment (){
        leaveCommentButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {

                String stringNewComment = comment.getText().toString();

                FirebaseDatabase.getInstance().getReference("Comments").child(currentProductName).
                        child(currUser.getUid()).child("comment").setValue(stringNewComment);


                FirebaseDatabase.getInstance().getReference("Comments").child(currentProductName).
                        child(currUser.getUid()).child("userInfo").setValue(userInfo);
                Toast.makeText(getContext(),"Your comment was succesfully saved." , Toast.LENGTH_LONG);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().finish();
            }
        });
    }


}
