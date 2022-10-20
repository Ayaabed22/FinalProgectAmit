package com.example.finalproject.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalproject.R;
import com.example.finalproject.interfaces.RetrofitClient;
import com.example.finalproject.model.dataResponse;
import com.example.finalproject.room.FavouriteDatabase;
import com.example.finalproject.room.ProductDao;
import com.example.finalproject.room.FavouriteModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductDetlies extends Fragment  {
    public ProductDetliesArgs args;
    TextView textViewTitle, textViewPrice;
    ImageView imageViewProduct, favouriteIcon;
    RatingBar ratingBarProduct;
    NavController navController;

    int id ;
    String title;
    Double price;
    String image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detlies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favouriteIcon = view.findViewById(R.id.favourite_icon);
        navController = Navigation.findNavController(view);
        imageViewProduct = view.findViewById(R.id.product_image);
        ratingBarProduct = view.findViewById(R.id.rate_product);
        textViewTitle = view.findViewById(R.id.product_name);
        textViewPrice = view.findViewById(R.id.product_price);
        args = ProductDetliesArgs.fromBundle(requireArguments());
        getProductDeatlis(args.getDetailsArgs());

        favouriteIcon.setOnClickListener(v -> {
            favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_24);
            ProductDao appDao = FavouriteDatabase.getDbInstance(requireContext()).productsDao();
            appDao.insertAll(new FavouriteModel(id,title, price, image));
            appDao.getAll();
        });
    }


    private void getProductDeatlis(int getDetails) {
        RetrofitClient.getInstance().getMyapi().getSingleProduct(getDetails).enqueue(new Callback<dataResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<dataResponse> call, @NonNull Response<dataResponse> response) {
                assert response.body() != null;
                id = getDetails;
                title = response.body().getTitle();
                price = response.body().getPrice();
                image = response.body().getImage();

                textViewTitle.setText(title);
                textViewPrice.setText(price.toString());
                ratingBarProduct.setRating(response.body().getRating().getRate().floatValue());
                Glide.with(requireContext()).load(image).into(imageViewProduct);

            }
            @Override
            public void onFailure(@NonNull Call<dataResponse> call, @NonNull Throwable t) {

            }

        });


    }
}






