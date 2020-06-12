package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {

    private TextView texEmail,textID;
    private Button btnLogout;

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        inicializarComponentes();
        eventoClick();
    }

    private void eventoClick() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Conexao.logOut();
                finish();
            }
        });
    }

    private void inicializarComponentes() {
        texEmail = (TextView) findViewById(R.id.text_Email);
        textID = (TextView) findViewById(R.id.textID);
        btnLogout = (Button) findViewById(R.id.btn_Logout);
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        verificaUser();

    }

    private void verificaUser() {
        if (user == null){
            finish();;
        }else{
            texEmail.setText("Email  "+user.getEmail());
            textID.setText(("ID:  "+user.getUid()));
        }

    }
}