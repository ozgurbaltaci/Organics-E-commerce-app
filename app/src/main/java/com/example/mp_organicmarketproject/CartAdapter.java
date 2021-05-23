package com.example.mp_organicmarketproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ImageViewHolder> {
    private Context context;
    private List<AddedProductInCart> addedProductInCart;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    FirebaseUser user;
    TextView totalPriceOfProducts;
    TextView deliveryPrice;
    double totalPrice = 0;





    public CartAdapter(Context context, List<AddedProductInCart> addedProductInCart,View view) {
        this.context = context;
        this.addedProductInCart = addedProductInCart;
        this.totalPriceOfProducts = view.findViewById(R.id.TotalPriceOfProducts);
        this.deliveryPrice = view.findViewById(R.id.deliveryPrice);

    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.showing_products_in_cart, parent, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        totalPriceOfProducts.setText("       ");
        return new ImageViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        AddedProductInCart currProduct = addedProductInCart.get(position);
        DatabaseReference UserCartReference = databaseReference.child("User Cart").child(user.getUid());

        String[] priceDivider = currProduct.getproductPrice().split("\\$");
        String currProductIntegerPrice = priceDivider[0] ;

        holder.productName.setText(currProduct.getproductName());

        Picasso.get().load(currProduct.getproductPhoto()).placeholder(R.drawable.imagepreview)
                .fit().centerCrop().into(holder.productPhoto);



        final int[] currentDesiredAmount = new int[1];
        UserCartReference.child(currProduct.getproductName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean check = false;
                if(snapshot.exists() && !check){
                    currentDesiredAmount[0] = Integer.parseInt(snapshot.child("desiredAmount").getValue().toString());
                    holder.desiredAmount.setText(currentDesiredAmount[0] + "");
                    double totalPriceOfSingleProduct = currentDesiredAmount[0] * Double.parseDouble(currProductIntegerPrice);
                    totalPrice += totalPriceOfSingleProduct;
                    totalPriceOfProducts.setText(totalPrice + "$");
                    if(totalPrice >= 30.0)
                        deliveryPrice.setText("0.0$");
                    else
                        deliveryPrice.setText("3$");

                    holder.totalPriceOfSingleProduct.setText((Double.parseDouble(currProductIntegerPrice)) * currentDesiredAmount[0] + "$");
                }
                else
                    check = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] currentDesiredAmount = new int[1];

                UserCartReference.child(currProduct.getproductName()).addListenerForSingleValueEvent(new ValueEventListener() {
                    boolean flag = false;
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists() && !flag) {
                            currentDesiredAmount[0] = Integer.parseInt(snapshot.child("desiredAmount").getValue().toString());
                            UserCartReference.child(currProduct.getproductName()).child("desiredAmount").setValue(currentDesiredAmount[0] + 1)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            holder.desiredAmount.setText(currentDesiredAmount[0] + 1 + "");
                                            currProduct.setDesiredAmount(currentDesiredAmount[0] + 1 );
                                            Toast.makeText(context, "Please refresh your cart list",Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }
                        else
                            flag = true;

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                 }
        });

        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] currentDesiredAmount = new int[1];

                UserCartReference.child(currProduct.getproductName()).addListenerForSingleValueEvent(new ValueEventListener() {
                    boolean flag = false;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists() && !flag) {
                            currentDesiredAmount[0] = Integer.parseInt(snapshot.child("desiredAmount").getValue().toString());
                            if (currentDesiredAmount[0] != 1){
                                UserCartReference.child(currProduct.getproductName()).child("desiredAmount").setValue(currentDesiredAmount[0] - 1)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                holder.desiredAmount.setText(currentDesiredAmount[0] - 1 + "");
                                                currProduct.setDesiredAmount(currentDesiredAmount[0] - 1 );
                                                Toast.makeText(context, "Please refresh your cart list", Toast.LENGTH_LONG).show();

                                            }
                                        });
                            }
                            else {
                                UserCartReference.child(currProduct.getproductName()).child("desiredAmount").setValue(0);
                                FirebaseDatabase.getInstance().getReference().child("User Cart").child(user.getUid()).child(currProduct.getproductName()).removeValue();

                            }

                        } else
                            flag = true;

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
        return addedProductInCart.size();
    }

    static
    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView productName;
        public ImageView productPhoto;
        public TextView totalPriceOfSingleProduct;
        public TextView desiredAmount;
        public Button plusButton;
        public Button minusButton;

        View view;



        @SuppressLint("SetTextI18n")
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productName = itemView.findViewById(R.id.productNameInCart);
            this.productPhoto = itemView.findViewById(R.id.productsPhotoInCart);
            this.totalPriceOfSingleProduct = itemView.findViewById(R.id.CartProductPrice);
            this.desiredAmount = itemView.findViewById(R.id.desiredAmountInCart);
            this.plusButton = itemView.findViewById(R.id.plusButton);
            this.minusButton = itemView.findViewById(R.id.minusButton);
            view = itemView;

        }

    }
}
