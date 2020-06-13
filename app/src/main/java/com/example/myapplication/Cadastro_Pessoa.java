package com.example.myapplication;

public class Cadastro_Pessoa {

    public String tipo;

    public Cadastro_Pessoa(){

    }


    public Cadastro_Pessoa(String tipo){
        this.tipo = tipo;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
