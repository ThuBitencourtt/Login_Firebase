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
import com.example.menus.interfaces.Comunicador2;
import com.example.menus.interfaces.Comunicador3;

/**
 * A simple {@link Fragment} subclass.
 */

public class Professor extends Fragment {
    private CardView cvConteudo;
    private CardView cvAtividade_professor;
    private CardView cvEntrega;
    private Comunicador comunicador;
    private Comunicador2 comunicador2;
    private Comunicador3 comunicador3;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            comunicador = (Comunicador) context;
        }catch (Exception ex){
            ex.getStackTrace();
        }
        try {
            comunicador2 = (Comunicador2) context;
        }catch (Exception ex){
            ex.getStackTrace();
        }
        try {
            comunicador3 = (Comunicador3) context;
        }catch (Exception ex){
            ex.getStackTrace();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        cvConteudo = root.findViewById(R.id.cvConteudo);
        cvAtividade_professor = root.findViewById(R.id.cvAtividade_professor);
        cvEntrega = root.findViewById(R.id.cvEntrega);

        cvConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comunicador.recebeNomeDaBanda("TESTE");
            }
        });


        cvAtividade_professor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comunicador2.atividade_professor("BANDA");
            }
        });

        cvEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comunicador3.entrega("TESTE1");
            }
        });
        return root;
    }

}

