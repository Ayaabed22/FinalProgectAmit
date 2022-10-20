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
import com.example.finalproject.room.FavouriteModel;

import java.util.List;

public class favouriteAdapter extends RecyclerView.Adapter<favouriteAdapter.favouriteViewHolder> {
    List<FavouriteModel> favouriteModelArrayList;
    Context context;

    public favouriteAdapter(List<FavouriteModel> favouriteModelList, Context context) {
        this.favouriteModelArrayList = favouriteModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public favouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.favouriteitem,parent,false);
        return new favouriteViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull favouriteViewHolder holder, int position) {
        holder.textViewPrice.setText(favouriteModelArrayList.get(position).getPrice().toString());
        holder.textViewTitle.setText(favouriteModelArrayList.get(position).getTitle());
        Glide.with(holder.itemView).load(favouriteModelArrayList.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return favouriteModelArrayList.size();
    }

    public class favouriteViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle,textViewPrice;

        public favouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.product_image);
            textViewTitle=itemView.findViewById(R.id.product_title);
            textViewPrice=itemView.findViewById(R.id.product_price);

        }
    }
}
