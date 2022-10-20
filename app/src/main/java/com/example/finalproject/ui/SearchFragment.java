package com.example.finalproject.ui;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.adapters.getProductAdapter;
import com.example.finalproject.interfaces.ProductClick;
import com.example.finalproject.interfaces.RetrofitClient;
import com.example.finalproject.model.dataResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {
    RecyclerView recyclerView;
    SearchView searchView;
    getProductAdapter getProductAdapter;
    NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.search_view);
        recyclerView = view.findViewById(R.id.sreach_recycler_view);
        navController= Navigation.findNavController(view);

        getProductsFromApi();

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


                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        getProductAdapter.getFilter().filter(newText);
                        return false;
                    }

                });
                return;

            }

            @Override
            public void onFailure(Call<ArrayList<dataResponse>> call, Throwable t) {

            }

        });
    }
    ProductClick productClick = new ProductClick() {
        @Override
        public void itemClick(int id) {
         SearchFragmentDirections .ActionSearchFragmentToProductDetlies action = SearchFragmentDirections.actionSearchFragmentToProductDetlies(id);
            navController.navigate(action);
        }
    };
}
