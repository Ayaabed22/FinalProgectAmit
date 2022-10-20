package com.example.finalproject.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.interfaces.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SigninFragment extends Fragment {
    Button button;
    TextView textViewSignUp;
    EditText editTextname, editTextpassword;
   NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.signInBtn);
        editTextname = view.findViewById(R.id.et_user_name);
        editTextpassword = view.findViewById(R.id.et_password);
        textViewSignUp=view.findViewById(R.id.tv_signUp);
        navController = Navigation.findNavController(view);


      textViewSignUp.setOnClickListener(v -> navController.navigate(R.id.action_signinFragment_to_signupFragment));

        button.setOnClickListener(v -> {
            String username=editTextname.getText().toString();
            String password=editTextpassword.getText().toString();
            checkData(username,password);
        });

    }

    private void checkData(String username, String password) {
        RetrofitClient.getInstance().getMyapi().getUser(new loginRequest(username, password)).enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(@NonNull Call<loginResponse> call, @NonNull Response<loginResponse> response) {
                if (response.isSuccessful()) {
                        navController.navigate(R.id.action_signinFragment_to_homeFragment);
                    }

            }

            @Override
            public void onFailure(@NonNull Call<loginResponse> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    }


