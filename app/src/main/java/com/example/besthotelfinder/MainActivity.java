package com.example.besthotelfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    RadioButton rb_admin;
    RadioButton rb_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_Entrar = (Button) findViewById(R.id.btn_Dados);
        rb_admin = findViewById(R.id.rb_admin);
        rb_usuario = findViewById(R.id.rb_usuario);

    }


    public void EnviaDados(View view) {

        if (rb_usuario.isChecked()){
            Intent busca = new Intent(getApplicationContext(), BuscaHotel.class);
            startActivity(busca);

        }
        if (rb_admin.isChecked()){
            Intent cadastro = new Intent(getApplicationContext(), CadastroHotel.class);
            startActivity(cadastro);
        }


    }

}