package com.example.mp_organicmarketproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

import org.w3c.dom.Text;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ImageViewHolder> {
    private Context context;
    private List<AddedProductInCart> addedProductInCart;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    FirebaseUser user;
    TextView totalPriceOfProducts;







    public CartAdapter(Context context, List<AddedProductInCart> addedProductInCart,View view) {
        this.context = context;
        this.addedProductInCart = addedProductInCart;
        this.totalPriceOfProducts = view.findViewById(R.id.TotalPriceOfProducts);

    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.showing_products_in_cart, parent, false);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
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
        holder.desiredAmount.setText( "" + holder.getCountForDesiredAmount());
        holder.totalPriceOfSingleProduct.setText(Double.parseDouble(currProductIntegerPrice) * holder.getCountForDesiredAmount() + "$");



        Picasso.get().load(currProduct.getproductPhoto()).placeholder(R.drawable.imagepreview)
                .fit().centerCrop().into(holder.productPhoto);



        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currDesiredAmount = holder.getCountForDesiredAmount();
                holder.setCountForDesiredAmount(currDesiredAmount +1);
                holder.desiredAmount.setText(""+holder.getCountForDesiredAmount());
                holder.totalPriceOfSingleProduct.setText(Double.parseDouble(currProductIntegerPrice) * holder.getCountForDesiredAmount() + "$");

            }
        });

        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.getCountForDesiredAmount() == 1)
                    FirebaseDatabase.getInstance().getReference().child("User Cart").child(user.getUid()).child(currProduct.getproductName()).removeValue();

                else {
                    int currDesiredAmount = holder.getCountForDesiredAmount();
                    holder.setCountForDesiredAmount(currDesiredAmount - 1);
                    holder.desiredAmount.setText("" + holder.getCountForDesiredAmount());
                    holder.totalPriceOfSingleProduct.setText(Double.parseDouble(currProductIntegerPrice) * holder.getCountForDesiredAmount() + "$");

                }
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
        public int countForDesiredAmount;
        public double totalPrice;


        View view;

        public int getCountForDesiredAmount() {
            return countForDesiredAmount;
        }

        public void setCountForDesiredAmount(int countForDesiredAmount) {
            this.countForDesiredAmount = countForDesiredAmount;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        @SuppressLint("SetTextI18n")
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productName = itemView.findViewById(R.id.productNameInCart);
            this.productPhoto = itemView.findViewById(R.id.productsPhotoInCart);
            this.totalPriceOfSingleProduct = itemView.findViewById(R.id.CartProductPrice);
            this.desiredAmount = itemView.findViewById(R.id.desiredAmountInCart);
            this.plusButton = itemView.findViewById(R.id.plusButton);
            this.minusButton = itemView.findViewById(R.id.minusButton);
            totalPrice = 0;

            countForDesiredAmount = 1;

            view = itemView;

        }

    }
}
