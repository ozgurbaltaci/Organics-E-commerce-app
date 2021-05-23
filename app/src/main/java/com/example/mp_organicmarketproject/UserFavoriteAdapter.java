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
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp_organicmarketproject.dto.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserFavoriteAdapter extends RecyclerView.Adapter<UserFavoriteAdapter.ImageViewHolder> {
    private Context context;
    private List<UserFavorite> userFavoritesList;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    FirebaseUser user;


    public UserFavoriteAdapter(Context context, List<UserFavorite> userFavorites) {
        this.context = context;
        this.userFavoritesList = userFavorites;
    }


    @NonNull
    @Override
    public UserFavoriteAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.products, parent, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        return new UserFavoriteAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserFavoriteAdapter.ImageViewHolder holder, int position) {

        UserFavorite currProduct = userFavoritesList.get(position);
        holder.productName.setText(currProduct.getproductName());
        holder.productPrice.setText(currProduct.getproductPrice());
        databaseReference.child("User Favorites").child(user.getUid()).child(currProduct.getproductName()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    holder.favoriteButton.setImageResource(R.drawable.fullheart);
                    holder.count++;
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
                holder.count++;
                UserFavorite userFavorite = new UserFavorite(currProduct.getproductName(),currProduct.getproductPhoto(),currProduct.getproductPrice());

                if(holder.count % 2 != 0) {
                    holder.favoriteButton.setImageResource(R.drawable.fullheart);
                    databaseReference.child("User Favorites").child(user.getUid()).child(currProduct.getproductName()).setValue(userFavorite);
                }

                else {
                    holder.favoriteButton.setImageResource(R.drawable.emptyheart);
                    databaseReference.child("User Favorites").child(user.getUid()).child(currProduct.getproductName()).removeValue();

                }

            }
        });

        holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference userCartReference = databaseReference.child("User Cart").child(user.getUid()).child(currProduct.getproductName());


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

    }

    @Override
    public int getItemCount() {
        return userFavoritesList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView productName;
        public ImageView productPhoto;
        public TextView productPrice;
        public ImageButton favoriteButton;
        public ImageButton addToCartButton;
        public int count;
        View view;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productName = itemView.findViewById(R.id.newProductName);
            this.productPhoto = itemView.findViewById(R.id.newProductsPhoto);
            this.productPrice = itemView.findViewById(R.id.newProductPrice);
            this.favoriteButton = itemView.findViewById(R.id.newProductsHeartShape);
            this.addToCartButton = itemView.findViewById(R.id.newProductscartShape);
            count = 0;
            view = itemView;

        }

    }
}
