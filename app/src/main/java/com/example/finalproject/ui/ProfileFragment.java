package com.example.finalproject.ui;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.interfaces.RetrofitClient;
import com.example.finalproject.signup.signUpRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    TextView textViewUserName, textViewEmail, textViewOrders, textViewDetails, textViewLogOut;
    NavController navController;
    BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewUserName = view.findViewById(R.id.Username_text_view);
        textViewEmail = view.findViewById(R.id.tv_userEmail);
        textViewOrders = view.findViewById(R.id.orders_user);
        textViewDetails = view.findViewById(R.id.my_details_user);
        textViewLogOut = view.findViewById(R.id.log_out);
        navController = Navigation.findNavController(view);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);


        bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.account:
                    navController.navigate(R.id.action_profileFragment_self);
                    return true;
                case R.id.cart:
                    navController.navigate(R.id.action_profileFragment_to_cartFragment);
                    return true;
                case R.id.favourite:
                    navController.navigate(R.id.action_profileFragment_to_favouriteFragment);
                    return true;
                case R.id.shop:
                    navController.navigate(R.id.action_profileFragment_to_homeFragment);
                    return true;
            }
            return false;
        });

        textViewLogOut.setOnClickListener(v ->
                navController.navigate(R.id.action_profileFragment_to_signinFragment));

        textViewOrders.setOnClickListener(v ->
                navController.navigate(R.id.action_profileFragment_to_cartFragment));

        textViewDetails.setOnClickListener(v ->
                navController.navigate(R.id.action_profileFragment_to_detailsFragment));

        getSingleUser();
    }

    private void getSingleUser() {
        RetrofitClient.getInstance().getMyapi().getSingleUser().enqueue(new Callback<signUpRequest>() {
            @Override
            public void onResponse(@NonNull Call<signUpRequest> call, @NonNull Response<signUpRequest> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    textViewUserName.setText(response.body().getUsername());
                    textViewEmail.setText(response.body().getEmail());

                }
            }

            @Override
            public void onFailure(@NonNull Call<signUpRequest> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailureProfile: " + t.getLocalizedMessage());

            }
        });
    }
}

