package com.example.menus.ui.professor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


import com.example.menus.R;
import com.example.menus.interfaces.Comunicador;

/**
 * A simple {@link Fragment} subclass.
 */

public class Professor extends Fragment {
    private CardView cvConteudo;
    private Comunicador comunicador;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            comunicador = (Comunicador) context;
        }catch (Exception ex){
            ex.getStackTrace();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        cvConteudo = root.findViewById(R.id.cvConteudo);

        cvConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comunicador.recebeNomeDaBanda("TESTE");
            }
        });

        return root;
    }

}

