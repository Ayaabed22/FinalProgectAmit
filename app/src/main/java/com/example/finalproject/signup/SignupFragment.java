package com.example.finalproject.signup;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.model.Geolocation;
import com.example.finalproject.R;
import com.example.finalproject.interfaces.RetrofitClient;
import com.example.finalproject.model.Address;
import com.example.finalproject.model.Name;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupFragment extends Fragment {
    Button buttonSignUp;
    TextView textViewSingIn,textViewSelectLocation;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonSignUp = view.findViewById(R.id.signUpBtn);
        textViewSingIn=view.findViewById(R.id.tv_signIn);
        navController = Navigation.findNavController(view);
        textViewSelectLocation=view.findViewById(R.id.select_user_location);

        textViewSelectLocation.setOnClickListener(v ->
                navController.navigate(R.id.action_signupFragment_to_mapsFragment));
        textViewSingIn.setOnClickListener(v -> navController.popBackStack());

        buttonSignUp.setOnClickListener(v -> {
            signUpRequest signUp = new signUpRequest("John@gmail.com", "johnd", "m38rmF$",
                    new Name("John", "Doe"),
                    new Address("12926-3874", 3, "kilcoole", "7835 new road",
                            new Geolocation("-37.3159", "81.1496")), "1-570-236-7033");

            RetrofitClient.getInstance().getMyapi().addUser(signUp).enqueue(new Callback<singUpResponse>() {
                @RequiresApi(api = Build.VERSION_CODES.R)
                @Override
                public void onResponse(@NonNull Call<singUpResponse> call, @NonNull Response<singUpResponse> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        Log.i(TAG, "onResponse: " + response.body());
                        Toast.makeText(requireContext(), "your data is filled", Toast.LENGTH_SHORT).show();

                    }
                }

                @RequiresApi(api = Build.VERSION_CODES.R)
                @Override
                public void onFailure(@NonNull Call<singUpResponse> call, @NonNull Throwable t) {
                    Log.i(TAG, "onFailure: " + t.getLocalizedMessage());
                }
            });


        });
    }

}