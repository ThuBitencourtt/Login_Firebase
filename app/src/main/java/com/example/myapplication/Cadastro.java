package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastro extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btnRegistrar, btnVoltar;
    private Spinner spinner_tipo;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private Cadastro_Pessoa cadastro_pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicilizarCompentes();
        eventoClick();

        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("Cadastro");

        cadastro_pessoa = new Cadastro_Pessoa();


    }

    private void eventoClick() {

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                criarUser(email, senha);
                Cadastrar(spinner_tipo.getSelectedItem().toString());
            }
        });



    }

    private void criarUser(String email, String senha) {
        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            alert("Usuário cadastrado com sucesso");
                           /* Intent i = new Intent(Cadastro.this, Perfil.class);
                            startActivity(i);*/
                            finish();


                        } else {
                            alert("Erro do cadastro");

                        }

                    }
                });
    }


    private void alert(String msg) {

        Toast.makeText(Cadastro.this, msg, Toast.LENGTH_SHORT).show();

    }


    private void inicilizarCompentes() {
        editEmail = (EditText) findViewById(R.id.editEmail_cadastro);
        editSenha = (EditText) findViewById(R.id.editSenha_cadastro);
        btnRegistrar = (Button) findViewById(R.id.btn_Resetar);
        btnVoltar = (Button) findViewById(R.id.btn_Voltar);
        spinner_tipo = (Spinner) findViewById(R.id.spin_tipo);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.tipo, android.R.layout.simple_spinner_item);
        spinner_tipo.setAdapter(adapter);
    }


    public void Cadastrar(String tipo){
        String key = myRef.child("Cadastro").push().getKey();
        cadastro_pessoa.setTipo(tipo);

        myRef.child(key).setValue(cadastro_pessoa);

        Toast.makeText(this, "Cadastro inserido com sucesso",Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}