
package com.example.finalproject.ui;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.finalproject.R;
import com.example.finalproject.interfaces.RetrofitClient;
import com.example.finalproject.adapters.getProductAdapter;
import com.example.finalproject.interfaces.ProductClick;
import com.example.finalproject.model.dataResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.Collections;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    getProductAdapter getProductAdapter;
    RecyclerView recyclerView, sortRecyclerview, recyclerViewGetDescription;
    NavController navController;
    BottomNavigationView bottomNavigationView;
    ImageView imageView;
    SearchView searchView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewGetDescription = view.findViewById(R.id.sortRatingRecyclerView);
        searchView = view.findViewById(R.id.search_view);
        imageView = view.findViewById(R.id.filter);
        recyclerView = view.findViewById(R.id.recyclerviewproduct);
        sortRecyclerview = view.findViewById(R.id.sortrecyclerview);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(view);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnClickListener(v -> navController.navigate(R.id.action_homeFragment_to_searchFragment));

        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.account:
                    navController.navigate(R.id.action_homeFragment_to_profileFragment);
                    return true;
                case R.id.cart:
                    navController.navigate(R.id.action_homeFragment_to_cartFragment);
                    return true;
                case R.id.favourite:
                    navController.navigate(R.id.action_homeFragment_to_favouriteFragment);
                    return true;
                case R.id.shop:
                    navController.navigate(R.id.action_homeFragment_to_homeFragment);
                    return true;
            }
            return false;
        });
        imageView.setOnClickListener(v -> navController.navigate(R.id.action_homeFragment_to_categoryFragment));

        getSort();
        getSortRate();
        getProductsFromApi();

    }

    private void getSortRate() {
        RetrofitClient.getInstance().getMyapi().sortData().enqueue(new Callback<ArrayList<dataResponse>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<ArrayList<dataResponse>> call, @NonNull Response<ArrayList<dataResponse>> response) {
                if (response.isSuccessful()) {

                    getProductAdapter = new getProductAdapter(response.body(), getContext(), productClick);
                    assert response.body() != null;
                    Collections.sort(response.body(), new dataResponse().productRating);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerViewGetDescription.setLayoutManager(layoutManager);
                    recyclerViewGetDescription.setAdapter(getProductAdapter);
                    getProductAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<dataResponse>> call, @NonNull Throwable t) {

            }
        });
    }

    private void getProductsFromApi() {
        RetrofitClient.getInstance().getMyapi().getData().enqueue(new Callback<ArrayList<dataResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<dataResponse>> call, @NonNull Response<ArrayList<dataResponse>> response) {
                if (response.isSuccessful()) {

                    getProductAdapter = new getProductAdapter(response.body(), getContext(), productClick);
                    recyclerView.setAdapter(getProductAdapter);
                    RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    Log.i(TAG, "onResponse: " + response.body());
                }



            }


            @Override
            public void onFailure(@NonNull Call<ArrayList<dataResponse>> call, @NonNull Throwable t) {

            }
        });
    }

    private void getSort() {
        RetrofitClient.getInstance().getMyapi().sortData().enqueue(new Callback<ArrayList<dataResponse>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<ArrayList<dataResponse>> call, @NonNull Response<ArrayList<dataResponse>> response) {
                if (response.isSuccessful()) {

                    getProductAdapter = new getProductAdapter(response.body(), getContext(), productClick);
                    assert response.body() != null;
                    Collections.sort(response.body(), new dataResponse().productitle);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    sortRecyclerview.setLayoutManager(layoutManager);
                    sortRecyclerview.setAdapter(getProductAdapter);
                    getProductAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<dataResponse>> call, @NonNull Throwable t) {

            }
        });
    }


    ProductClick productClick = new ProductClick() {
        @Override
        public void itemClick(int id) {
            HomeFragmentDirections.ActionHomeFragmentToProductDetlies action = HomeFragmentDirections.actionHomeFragmentToProductDetlies(id);
            navController.navigate(action);
        }
    };
}