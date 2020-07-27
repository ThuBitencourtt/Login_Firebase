package com.example.menus;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.menus.MainActivity.BANDA_KEY;

public class Entrega extends Fragment {

    private TextView txt_hello;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entrega, container, false);

        initViews(view);

        if (!getArguments().isEmpty()) {
            String nomeBanda = getArguments().getString(BANDA_KEY);
            txt_hello.setText(nomeBanda);
        }


        return view;
    }

    private void initViews(View view) {
        txt_hello = view.findViewById(R.id.text_hello);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }


}