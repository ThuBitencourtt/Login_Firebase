package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Login extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btnLogar, btnCadastro;
    private TextView txtResetSenha;

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarComponentes();
        eventoClicl();
    }

    private void eventoClicl() {
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Cadastro.class);
                startActivity(i);
            }
        });
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                login(email,senha);
            }
        });

        txtResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, ResetSenha.class);
                startActivity(i);
            }
        });


    }

    private void login(String email, String senha) {
        auth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent i = new Intent(Login.this, Perfil.class);
                            startActivity(i);
                        }else {
                            alert("e-mail ou senha errados");
                        }
                    }
                });
    }

    private void alert(String s) {
        Toast.makeText(Login.this,s,Toast.LENGTH_SHORT).show();
    }

    private void inicializarComponentes() {
        editEmail = (EditText) findViewById(R.id.editEmail_Login);
        editSenha = (EditText) findViewById(R.id.editSenha_Login);
        btnLogar = (Button) findViewById(R.id.btn_Logar);
        btnCadastro = (Button) findViewById(R.id.brn_Cadastrar);
        txtResetSenha = (TextView) findViewById(R.id.textEsqueciaSenha);
        
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}