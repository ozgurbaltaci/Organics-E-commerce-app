package com.example.mp_organicmarketproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewArrivalsAdapter extends RecyclerView.Adapter<NewArrivalsAdapter.ImageViewHolder> {
    private Context context;
    private List<NewProducts> productsList;

    public NewArrivalsAdapter(Context context, List<NewProducts> productsList) {
        this.context = context;
        this.productsList = productsList;
    }



    @NonNull
    @Override
    public NewArrivalsAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.new_products, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewArrivalsAdapter.ImageViewHolder holder, int position) {

        NewProducts currProduct = productsList.get(position);
        holder.productName.setText(currProduct.getproductName());
        holder.productPrice.setText(currProduct.getproductPrice());
        Picasso.get().load(currProduct.getproductPhoto()).placeholder(R.drawable.imagepreview)
                .fit().centerCrop().into(holder.productPhoto);

        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.count++;
                if(holder.count % 2 != 0)
                    holder.favoriteButton.setImageResource(R.drawable.smallfullheart);
                else
                    holder.favoriteButton.setImageResource(R.drawable.smallemptyheart);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productsList.size();
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
            productPrice = itemView.findViewById(R.id.newProductsPrice);
            favoriteButton = itemView.findViewById(R.id.newProductsHeartShape);
            count = 0;
            view = itemView;

        }

    }
}
