package com.example.finalproject.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.adapters.favouriteAdapter;
import com.example.finalproject.room.ProductDao;
import com.example.finalproject.room.FavouriteDatabase;

public class FavouriteFragment extends Fragment {
    RecyclerView recyclerViewFavourite;
    favouriteAdapter favouriteAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProductDao appDao = FavouriteDatabase.getDbInstance(requireContext()).productsDao();

        recyclerViewFavourite = view.findViewById(R.id.recyclerview_favourite);
        favouriteAdapter=new favouriteAdapter(appDao.getAll(),requireContext());
        recyclerViewFavourite.setAdapter(favouriteAdapter);

    }
    }