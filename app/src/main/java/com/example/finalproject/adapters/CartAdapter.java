package com.example.finalproject.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.example.finalproject.model.dataResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
ArrayList<dataResponse>cartList;
Context context;

    public CartAdapter(ArrayList<dataResponse> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(context).inflate(R.layout.cartitem,parent,false);
      return  new CartViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.textViewCount.setText(cartList.get(position).getQuantity().toString());
        holder.textViewPrice.setText(cartList.get(position).getPrice().toString());
        holder.textViewTitle.setText(cartList.get(position).getTitle());
        Glide.with(holder.itemView).load(cartList.get(position).getImage()).into(holder.imageViewProduct);

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct,imageViewFavourite,imageViewDelete;
        TextView textViewTitle,textViewCount,textViewPrice;
        FloatingActionButton floatingActionButtonAdd,floatingActionButtonMuns;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct=itemView.findViewById(R.id.product_image);
            imageViewDelete=itemView.findViewById(R.id.delete_icon);
            imageViewFavourite=itemView.findViewById(R.id.favourite_icon);
            textViewPrice=itemView.findViewById(R.id.product_price);
            textViewTitle=itemView.findViewById(R.id.product_title);
            textViewCount=itemView.findViewById(R.id.count);
            floatingActionButtonAdd=itemView.findViewById(R.id.addButton);
            floatingActionButtonMuns=itemView.findViewById(R.id.minusButton);

        }
    }
}
