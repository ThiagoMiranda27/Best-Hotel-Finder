package com.example.besthotelfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.besthotelfinder.gerenciador.DAO.HotelDAO;
import com.example.besthotelfinder.gerenciador.GerenciadorDasDatas;
import com.example.besthotelfinder.gerenciador.GerenciadorMelhorHotel;
import com.example.besthotelfinder.gerenciador.HoteisExistentes;
import com.example.besthotelfinder.gerenciador.Hotel;
import com.example.besthotelfinder.gerenciador.Taxa;
import com.example.besthotelfinder.gerenciador.TipoDeCliente;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuscaHotel extends AppCompatActivity {
    AlertDialog alerta;
    Calendar calendar_dataAtual;
    TextView tv_DataAtual;
    RadioButton rb_vip, rb_regular;
    String dataEntrada, dataSaida;
    TipoDeCliente tipoCliente;
    static final int DATE_ID = 0;
    private static String URL_REGIST = "http://192.168.64.3/besthotel/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_hotel);

        rb_vip = findViewById(R.id.rb_vip);
        rb_regular = findViewById(R.id.rb_regular);

        final Calendar dataInicio;
        final Calendar dataFim;
        Button btnEntrada = (Button) findViewById(R.id.btn_DataInicio);
        Button btnSaida = (Button) findViewById(R.id.btn_DataFim);
        Button btnEnviar = (Button) findViewById(R.id.btn_Enviar);
        final TextView tv_Entrada = (TextView) findViewById(R.id.tv_DateInicio);
        final TextView tv_Saida = (TextView) findViewById(R.id.tv_DateFim);

        dataInicio = Calendar.getInstance();

        btnEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mostrarData(tv_Entrada, dataInicio);
            }
        });

        dataFim = Calendar.getInstance();

        btnSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarData(tv_Saida, dataFim);
            }
        });
        atualizar(tv_Entrada, dataInicio);
        atualizar(tv_Saida, dataFim);

        btnEnviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(rb_regular.isChecked()){
                    tipoCliente = TipoDeCliente.REGULAR;
                }else if(rb_vip.isChecked()){
                    tipoCliente = TipoDeCliente.VIP;
                }
                dataEntrada = tv_Entrada.getText().toString();
                dataSaida = tv_Saida.getText().toString();

                GerenciadorDasDatas gerenciaDatasEscolhidas = new GerenciadorDasDatas();
                gerenciaDatasEscolhidas.comparaDatas(dataEntrada, dataSaida);

                Date dataInicioHospedagem = gerenciaDatasEscolhidas.stringParaDate(dataEntrada);
                Date dataFimHospedagem = gerenciaDatasEscolhidas.stringParaDate(dataSaida);
                List<Date> periodo = gerenciaDatasEscolhidas.pegarPeriodoAlocacao(dataInicioHospedagem, dataFimHospedagem);
                List<Hotel> lista;
                HotelDAO hotelDAO = new HotelDAO();

                lista = hotelDAO.getHotel(getApplicationContext());
                System.out.println(lista.toString());


//                busca();


//                HoteisExistentes hoteisExistentes = new HoteisExistentes();
//
//                GerenciadorMelhorHotel gerenciadorMelhorHotel = new GerenciadorMelhorHotel();
//                Taxa melhorTaxa = gerenciadorMelhorHotel.pegarMelhorTaxa(tipoCliente, periodo, hoteisExistentes.hoteis());
//                System.out.println("O Hotel mais barato encontrado foi: " + melhorTaxa.getHotel());
//                System.out.println("O seu preco ficou em: " + melhorTaxa.getPreco() + "R$");
//
//                AlertDialog.Builder alert = new AlertDialog.Builder(BuscaHotel.this);
//
//                alert.setTitle("Busca Hotel Banco");
//
//                alert.setMessage("");


//                alert.setTitle("Resultado da Procura");
//                String message = "O Hotel mais barato encontrado foi: " + melhorTaxa.getHotel() + "\n" +
//                        "O seu preco ficou em: " + melhorTaxa.getPreco() + "R$";
//                alert.setMessage(message);
//                alerta = alert.create();
//                alerta.show();


            }
        });
    }


    public void atualizar(TextView data, Calendar calendar){
        data.setText(new StringBuilder().append(calendar.get(Calendar.DAY_OF_MONTH)).append("/")
                .append(calendar.get(Calendar.MONTH)+1).append("/")
                .append(calendar.get(Calendar.YEAR)).append(" "));
    }


    public void mostrarData(TextView tv_data, Calendar calendar) {

        tv_DataAtual = tv_data;
        calendar_dataAtual = calendar;
        showDialog(DATE_ID);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
            calendar_dataAtual.set(Calendar.YEAR, ano);
            calendar_dataAtual.set(Calendar.MONTH, mes);
            calendar_dataAtual.set(Calendar.DAY_OF_MONTH, dia);
            atualizar(tv_DataAtual, calendar_dataAtual);
            unDisplay();
        }
    };

    private void unDisplay() {
        tv_DataAtual = null;
        calendar_dataAtual = null;
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        switch (id){
            case DATE_ID:
                return new DatePickerDialog((this), dateSetListener, calendar_dataAtual.get(Calendar.YEAR), calendar_dataAtual.get(Calendar.MONTH), calendar_dataAtual.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case DATE_ID:
                ((DatePickerDialog) dialog).updateDate(calendar_dataAtual.get(Calendar.YEAR), calendar_dataAtual.get(Calendar.MONTH), calendar_dataAtual.get(Calendar.DAY_OF_MONTH));
                break;
        }
    }

    public void busca(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(BuscaHotel.this, "Feito", Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(BuscaHotel.this, "Erro" + e.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BuscaHotel.this, "Voley ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.get("nome");
                params.get("classificacao");
                params.get("precoDiaSemanaRegular");
                params.get("precoDiaSemanaReward");
                params.get("precoFimSemanaRegular");
                params.get("precoFimSemanaReward");

//                params.put("nome", etNomeHotel.getText().toString());
//                params.put("classificacao", clas);
//                params.put("precoDiaSemanaRegular", precoDiaSemanaRegular);
//                params.put("precoDiaSemanaReward", precoDiaSemanaReward);
//                params.put("precoFimSemanaRegular", precoFimSemanaRegular);
//                params.put("precoFimSemanaReward", precoFimSemanaReward);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(BuscaHotel.this);
        requestQueue.add(stringRequest);
    }
}
