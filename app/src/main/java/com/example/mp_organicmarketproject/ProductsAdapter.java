package com.example.mp_organicmarketproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ImageViewHolder> {
    private Context context;
    private List<Product> products;


    public ProductsAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }


    @NonNull
    @Override
    public ProductsAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.products, parent, false);
        return new ProductsAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ImageViewHolder holder, int position) {

        Product currProduct = products.get(position);
        holder.productName.setText(currProduct.getproductName());
        holder.productPrice.setText(currProduct.getproductPrice());
        Picasso.get().load(currProduct.getproductPhoto()).placeholder(R.drawable.imagepreview)
                .fit().centerCrop().into(holder.productPhoto);

        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.count++;
                if(holder.count % 2 != 0)
                    holder.favoriteButton.setImageResource(R.drawable.fullheart);
                else
                    holder.favoriteButton.setImageResource(R.drawable.emptyheart);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView productName;
        public ImageView productPhoto;
        public TextView productPrice;
        public ImageButton favoriteButton;
        public int count;
        View view;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productName = itemView.findViewById(R.id.newProductName);
            this.productPhoto = itemView.findViewById(R.id.newProductsPhoto);
            this.productPrice = itemView.findViewById(R.id.newProductPrice);
            this.favoriteButton = itemView.findViewById(R.id.newProductsHeartShape);
            count = 0;
            view = itemView;

        }

    }
}
