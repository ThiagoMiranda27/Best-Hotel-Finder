package com.example.besthotelfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CadastroHotel extends AppCompatActivity {

    Button btnCadastrar;
    EditText etNomeHotel, etValorSemanal, etValorSemanalFidelidade, etValorFinalSemana, etValorFinalSemanaFidelidade;
    RadioButton rbEstrela3, rbEstrela4, rbEstrela5;
    private static String URL_REGIST = "http://192.168.0.23/besthotel/register.php";

    int classificacao = 0;

    double ValorSemanal, ValorSemanalFidelidade, ValorFinalSemana, ValorFinalSemanaFidelidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNomeHotel = findViewById(R.id.et_nomeHotel);
        etValorSemanal = findViewById(R.id.et_valorSemanal);
        etValorSemanalFidelidade = findViewById(R.id.et_valorSemanalFidelidade);
        etValorFinalSemana = findViewById(R.id.et_valorFinalsemana);
        etValorFinalSemanaFidelidade = findViewById(R.id.et_valorFinalsemanaFidelidade);
        rbEstrela3 = findViewById(R.id.rb_estrela3);
        rbEstrela4 = findViewById(R.id.rb_estrela4);
        rbEstrela5 = findViewById(R.id.rb_estrela5);
        btnCadastrar = findViewById(R.id.btn_Cadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNomeHotel.getText().length() == 0 || etValorSemanal.getText().length() == 0 || etValorSemanalFidelidade.getText().length() == 0
                        || etValorFinalSemana.getText().length() == 0 || etValorFinalSemanaFidelidade.getText().length() == 0 || !rbEstrela3.isChecked() && !rbEstrela4.isChecked() && !rbEstrela5.isChecked()) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(CadastroHotel.this);

                    alert.setTitle("Erro");
                    alert.setMessage("Preencha todos os campos");

                    alert.setNeutralButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }

                    });
                    alert.show();


                } else {

                    if (rbEstrela3.isChecked()) {
                        classificacao = 3;
                    } else if (rbEstrela4.isChecked()) {
                        classificacao = 4;
                    } else if (rbEstrela5.isChecked()) {
                        classificacao = 5;
                    } else {
                        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ----  \n\n  Selecione a classificacao do Hotel");
                    }

                    ValorSemanal = Double.parseDouble(etValorSemanal.getText().toString());
                    ValorSemanalFidelidade = Double.parseDouble(etValorSemanalFidelidade.getText().toString());
                    ValorFinalSemana = Double.parseDouble(etValorFinalSemana.getText().toString());
                    ValorFinalSemanaFidelidade = Double.parseDouble(etValorFinalSemanaFidelidade.getText().toString());


                    Regist();

                }
            }
            });

    }

    public void Regist() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(CadastroHotel.this, "Cadastrado com sucesso", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(CadastroHotel.this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CadastroHotel.this, "Voley ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String clas = String.valueOf(classificacao);
                String precoDiaSemanaRegular = String.valueOf(ValorSemanal);
                String precoDiaSemanaReward = String.valueOf(ValorSemanalFidelidade);
                String precoFimSemanaRegular = String.valueOf(ValorFinalSemana);
                String precoFimSemanaReward = String.valueOf(ValorFinalSemanaFidelidade);

                params.put("nome", etNomeHotel.getText().toString());
                params.put("classificacao", clas);
                params.put("precoDiaSemanaRegular", precoDiaSemanaRegular);
                params.put("precoDiaSemanaReward", precoDiaSemanaReward);
                params.put("precoFimSemanaRegular", precoFimSemanaRegular);
                params.put("precoFimSemanaReward", precoFimSemanaReward);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(CadastroHotel.this);
        requestQueue.add(stringRequest);
    }
}