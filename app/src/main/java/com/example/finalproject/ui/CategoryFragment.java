package com.example.finalproject.ui;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.example.finalproject.R;
import com.example.finalproject.adapters.getProductAdapter;
import com.example.finalproject.interfaces.ProductClick;
import com.example.finalproject.interfaces.RetrofitClient;
import com.example.finalproject.model.dataResponse;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {
    CheckBox checkBoxMen, checkBoxWomen, checkBoxJewelary, checkBoxElectronics;
    String category = "men's clothing";
    RecyclerView recyclerView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkBoxElectronics = view.findViewById(R.id.check_electronics);
        checkBoxMen = view.findViewById(R.id.check_Men);
        checkBoxWomen = view.findViewById(R.id.check_Women);
        checkBoxJewelary = view.findViewById(R.id.check_jewelery);
        recyclerView = view.findViewById(R.id.recyclerviewCategory);

        checkBoxJewelary.setOnClickListener(v -> {
            if (checkBoxJewelary.isChecked()){
                category = "jewelery";
                getSingleProduct(category);
            }
        });
        checkBoxElectronics.setOnClickListener(v -> {
            if (checkBoxElectronics.isChecked()) {
                category = "electronics";
                getSingleProduct(category);
            }
        });
        checkBoxMen.setOnClickListener(v -> {
            if (checkBoxMen.isChecked()) {
                category = "men's clothing";
                getSingleProduct(category);
            }
        });
        checkBoxWomen.setOnClickListener(v -> {
            if (checkBoxMen.isChecked()) {
                category = "women's clothing";
                getSingleProduct(category);
            }
        });
    }

    private void getSingleProduct(String category) {
        RetrofitClient.getInstance().getMyapi().getInCategory(category).enqueue(new Callback<ArrayList<dataResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<dataResponse>> call, @NonNull Response<ArrayList<dataResponse>> response) {
                if (response.isSuccessful()){
                    getProductAdapter getProductAdapter = new getProductAdapter(response.body(), getContext(), productClick);
                    Log.i(TAG, "onResponse: " + response.body());
                    RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(getProductAdapter);
                    Log.i(TAG, "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<dataResponse>> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }



    private final ProductClick productClick = id -> {

    };

}
