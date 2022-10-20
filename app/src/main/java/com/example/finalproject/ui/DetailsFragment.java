package com.example.finalproject.ui;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.interfaces.RetrofitClient;
import com.example.finalproject.signup.signUpRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsFragment extends Fragment {
    TextView textViewEmail,textViewFirstName,textViewLastName,textViewPassword,textViewUserName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewEmail=view.findViewById(R.id.email);
        textViewUserName=view.findViewById(R.id.userName);
        textViewFirstName=view.findViewById(R.id.firstName);
        textViewLastName=view.findViewById(R.id.lastName);
        textViewPassword=view.findViewById(R.id.password);

        getSingleUser();
    }

    private void getSingleUser() {
        RetrofitClient.getInstance().getMyapi().getSingleUser().enqueue(new Callback<signUpRequest>() {
            @Override
            public void onResponse(@NonNull Call<signUpRequest> call, @NonNull Response<signUpRequest> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    textViewEmail.setText(response.body().getEmail());
                    textViewUserName.setText(response.body().getUsername());
                    textViewFirstName.setText(response.body().getName().getFirstname());
                    textViewLastName.setText(response.body().getName().getLastname());
                    textViewPassword.setText(response.body().getPassword());

                }
            }

            @Override
            public void onFailure(@NonNull Call<signUpRequest> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailureProfile: " + t.getLocalizedMessage());

            }
        });
    }
}