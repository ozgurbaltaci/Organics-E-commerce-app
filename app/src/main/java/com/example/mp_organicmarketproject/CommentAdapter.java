package com.example.mp_organicmarketproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp_organicmarketproject.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ImageViewHolder> {
    private Context context;
    private List<Comment> commentList;
    DatabaseReference databaseReference;
    FirebaseUser currUser;
    FirebaseAuth mAuth;
   


    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
        mAuth = FirebaseAuth.getInstance();
        currUser = mAuth.getCurrentUser();
    }


    @NonNull
    @Override
    public CommentAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_layout, parent, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        return new CommentAdapter.ImageViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Comment currComment = commentList.get(position);
        holder.comment.setText(currComment.getComment());
        holder.currUserText.setText(currComment.getUserInfo() + ": ");



    }




    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView comment;
        public TextView currUserText;

        View view;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.comment = itemView.findViewById(R.id.comment);
            this.currUserText = itemView.findViewById(R.id.userWithName);

            view = itemView;

        }

    }
}
