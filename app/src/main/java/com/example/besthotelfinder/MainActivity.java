package com.example.besthotelfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn_Entrar = (Button) findViewById(R.id.btn_Dados);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.rb_admin:
                Intent adm = new Intent(MainActivity.this, CadastroHotel.class);
                startActivity(adm);
                break;
            case R.id.rb_usuario:
                Intent usuario = new Intent(MainActivity.this, BuscaHotel.class);
                startActivity(usuario);
                break;

        }
}}