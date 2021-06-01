package com.example.mp_organicmarketproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.ImageViewHolder> {
    private Context context;
    private List<FAQ> faqList;
    DatabaseReference databaseReference;
   


    public FaqAdapter(Context context, List<FAQ> faqList) {
        this.context = context;
        this.faqList = faqList;
    }


    @NonNull
    @Override
    public FaqAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_of_faq, parent, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        return new FaqAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        FAQ currQuestion = faqList.get(position);
        holder.question.setText(currQuestion.getQuestion());
        holder.answer.setText(currQuestion.getAnswer());
        boolean isExpended = currQuestion.isExpanded();
        holder.expandableLayout.setVisibility(isExpended ? View.VISIBLE : View.GONE);


    }




    @Override
    public int getItemCount() {
        return faqList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView question;
        public TextView answer;
        public ConstraintLayout expandableLayout;
        View view;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.question = itemView.findViewById(R.id.question);
            this.answer = itemView.findViewById(R.id.answer);
            this.expandableLayout = itemView.findViewById(R.id.expandableLayout);
            view = itemView;

            question.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FAQ faq = faqList.get(getAdapterPosition());
                    faq.setExpanded(!faq.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }

    }
}
