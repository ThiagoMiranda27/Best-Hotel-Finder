package com.example.besthotelfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity {
    RadioButton rb_admin;
    RadioButton rb_usuario;

    private static String URL_REGIST = "http://192.168.64.3/android/register.php";

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

//    public void Regist(){
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    Toast.makeText(MainActivity.this, "Feito", Toast.LENGTH_LONG).show();
//                } catch (JSONException e) {
//                    Toast.makeText(MainActivity.this, "Erro" + e.getMessage(), Toast.LENGTH_LONG).show();
//
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "Voley ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        }) {
//            public Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("nome", var_nome);
//                params.put("email", var_email);
//                params.put("realistico", valor1.toString());
//                params.put("investigativo", valor2.toString());
//                params.put("empreendedor", valor3.toString());
//                params.put("convencional", valor4.toString());
//                params.put("artistico", valor5.toString());
//                params.put("social", valor6.toString());
//                return params;
//            }
//
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(HotelDAO.this);
//        requestQueue.add(stringRequest);
//    }
}