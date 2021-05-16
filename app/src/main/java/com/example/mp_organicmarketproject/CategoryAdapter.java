package com.example.mp_organicmarketproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ImageViewHolder> {
    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }



    @NonNull
    @Override
    public CategoryAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.categories, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ImageViewHolder holder, int position) {

        Category categoryCur = categories.get(position);

        holder.categoryName.setText(categoryCur.getCategoryName());
        Picasso.get().load(categoryCur.getCategoryPhoto()).placeholder(R.drawable.imagepreview)
                .fit().centerCrop().into(holder.categoryPhoto);



        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showingProductsIntent = new Intent (context, ProductsActivity.class);
                showingProductsIntent.putExtra("Category", categoryCur.getCategoryName());
                context.startActivity(showingProductsIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView categoryName;
        public ImageView categoryPhoto;
        public LinearLayout iconWrapper;
        View view;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.categoryName = itemView.findViewById(R.id.ProductCategoryName);
            this.categoryPhoto = itemView.findViewById(R.id.categoryPhoto);
            iconWrapper = itemView.findViewById(R.id.iconWrapper);
            view = itemView;

        }

    }
}
