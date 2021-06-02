package com.example.mp_organicmarketproject;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class NewArrivalsAdapter extends RecyclerView.Adapter<NewArrivalsAdapter.ImageViewHolder> {
    private Context context;
    private List<NewProducts> productsList;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    FirebaseUser user;

    public NewArrivalsAdapter(Context context, List<NewProducts> productsList) {
        this.context = context;
        this.productsList = productsList;

    }


    @NonNull
    @Override
    public NewArrivalsAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.new_products, parent, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewArrivalsAdapter.ImageViewHolder holder, int position) {

        NewProducts currProduct = productsList.get(position);
        holder.productName.setText(currProduct.getproductName());
        holder.productPrice.setText(currProduct.getproductPrice());

        databaseReference.child("User Favorites").child(user.getUid()).child(currProduct.getproductName())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            holder.favoriteButton.setImageResource(R.drawable.smallfullheart);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        Picasso.get().load(currProduct.getproductPhoto()).placeholder(R.drawable.imagepreview)
                .fit().centerCrop().into(holder.productPhoto);

        holder.favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference userFavoritesReference = databaseReference.child("User Favorites").child(user.getUid()).child(currProduct.getproductName());
                UserFavorite userFavorite = new UserFavorite(currProduct.getproductName(),currProduct.getproductPhoto(),currProduct.getproductPrice());

                userFavoritesReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!snapshot.exists()){
                            holder.favoriteButton.setImageResource(R.drawable.smallfullheart);
                            userFavoritesReference.setValue(userFavorite);
                        }
                        else{
                            holder.favoriteButton.setImageResource(R.drawable.smallemptyheart);
                            userFavoritesReference.removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference userCartReference = databaseReference.child("User Cart").child(user.getUid()).child(currProduct.getproductName());

                String[] getDoublePrice = currProduct.getproductPrice().split("\\$");
                double doublePriceOfCurrProduct = Double.parseDouble(getDoublePrice[0]);
                AddedProductInCart addedProductInCart = new AddedProductInCart(currProduct.getproductName(),currProduct.getproductPhoto(),currProduct.getproductPrice(),1);
                userCartReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists()){

                            userCartReference.setValue(addedProductInCart);
                            Toast.makeText(v.getContext(), addedProductInCart.getproductName() + " added to your cart !",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(v.getContext(), "You have already added the " + addedProductInCart.getproductName()
                                    + "  to your cart.You can remove it or increase desired amount of it in your cart! " , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDetails = new Intent (context, ProductsDetailsActivity.class);
                goToDetails.putExtra("Clicked product", currProduct.getproductName());
                context.startActivity(goToDetails);
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
        public ImageButton addToCartButton;
        View view;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productName = itemView.findViewById(R.id.newProductName);
            this.productPhoto = itemView.findViewById(R.id.newProductsPhoto);
            productPrice = itemView.findViewById(R.id.newProductsPrice);
            favoriteButton = itemView.findViewById(R.id.newProductsHeartShape);
            addToCartButton = itemView.findViewById(R.id.newProductscartShape);
            view = itemView;

        }

    }
}
