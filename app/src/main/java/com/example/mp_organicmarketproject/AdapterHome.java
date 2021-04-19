

package com.example.mp_organicmarketproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.MyViewHolder> {

    private List<Slider> listItems;
    public AdapterHome(List<Slider> imageList){
        this.listItems = imageList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.catogeryimagelayout, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        Picasso.get().load(listItems.get(position).getImageURL()).into(holder.imgIcon);
        holder.title.setText(listItems.get(position).getTitle());

    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imgIcon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textView9);

            imgIcon = (ImageView) itemView.findViewById(R.id.productImage);



        }
    }
}


//
//package com.example.mp_organicmarketproject;
//
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.viewpager.widget.PagerAdapter;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//public class AdapterHome extends PagerAdapter {
//
//    List<Slider> listItems;
//
//    List<String> titleItems;
//
//    AdapterHome(List<Slider> imageList){
//        this.listItems = imageList;
//
//    }
//    @Override
//    public int getCount() {
//        return listItems.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.catogeryimagelayout, container, false);
//        ImageView image = view.findViewById(R.id.productImage);
//        TextView text = view.findViewById(R.id.textOfImage);
//        Picasso.get().load(listItems.get(position).getImageURL()).into(image);
//        text.setText(listItems.get(position).getTitle());
//        container.addView(view);
//        return view;
//    }
//
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View)object);
//    }
//}
//






















/*
package com.example.mp_organicmarketproject;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;*/
/*
public class AdapterHome extends PagerAdapter {

    List<Integer> listItems;

    AdapterHome(List<Integer> imageList){
        this.listItems = imageList;
    }
    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.catogeryimagelayout, container, false);
        ImageView image = view.findViewById(R.id.productImage);
        image.setImageResource(listItems.get(position));


        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
*/


