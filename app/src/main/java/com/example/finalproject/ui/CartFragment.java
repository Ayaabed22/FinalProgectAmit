package com.example.finalproject.ui;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.adapters.CartAdapter;
import com.example.finalproject.interfaces.RetrofitClient;
import com.example.finalproject.model.CartResponse;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.dataResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment {
    RecyclerView recyclerView;
    CartAdapter cartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.CartRV);
        getUserCart();
    }

    private void getUserCart() {
        RetrofitClient.getInstance().getMyapi().getUserCarts(3).enqueue(new Callback<ArrayList<CartResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<CartResponse>> call, @NonNull Response<ArrayList<CartResponse>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: " + response.body());
                    if (response.body() != null) {
                        ArrayList<Product> cartItems = new ArrayList<>();

                        for (int i = 0; i < response.body().size(); i++) {
                            cartItems.addAll(response.body().get(i).getProducts());
                            Log.i(TAG, "onResponseCart: " + cartItems);

                        }
                        getProductDetails(cartItems);

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<CartResponse>> call, @NonNull Throwable t) {

            }
        });
    }

    private void getProductDetails(ArrayList<Product> cartItems) {

        RetrofitClient.getInstance().getMyapi().getData().enqueue(new Callback<ArrayList<dataResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<dataResponse>> call, @NonNull Response<ArrayList<dataResponse>> response) {
                if (response.isSuccessful()) {
                    ArrayList<dataResponse> allProductList = response.body();
                    ArrayList<dataResponse> newCartItemList = new ArrayList<>();
                    Log.i(TAG, "onResponse: " + allProductList);
                    for (int i = 0; i < cartItems.size(); i++) {
                        assert allProductList != null;
                        int productId = cartItems.get(i).getProductId();
                        dataResponse cartItem = allProductList.get(productId);
                        cartItem.setQuantity(cartItems.get(i).getQuantity());
                        Log.i(TAG, "onResponse: " + cartItem);
                        newCartItemList.add(cartItem);
                    }
                    Log.i(TAG, "onResponse: " + newCartItemList);
                    setDataOnRV(newCartItemList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<dataResponse>> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void setDataOnRV(ArrayList<dataResponse> newCartItemList) {
        cartAdapter = new CartAdapter(newCartItemList, requireContext());
        recyclerView.setAdapter(cartAdapter);
    }
}
