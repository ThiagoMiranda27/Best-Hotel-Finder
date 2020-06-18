package com.example.besthotelfinder;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
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

    ArrayList<Hotel> hoteis = new ArrayList<Hotel>();

    ListView listView;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_hotel);

        rb_vip = findViewById(R.id.rb_vip);
        rb_regular = findViewById(R.id.rb_regular);



        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        new Connection().execute();
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
                Boolean teste = gerenciaDatasEscolhidas.comparaDatas(dataEntrada, dataSaida);
                if(teste == true)
                {
                    Toast.makeText(BuscaHotel.this,"Erro na data", Toast.LENGTH_SHORT).show();
                }
                else {


                    Date dataInicioHospedagem = gerenciaDatasEscolhidas.stringParaDate(dataEntrada);
                    Date dataFimHospedagem = gerenciaDatasEscolhidas.stringParaDate(dataSaida);
                    List<Date> periodo = gerenciaDatasEscolhidas.pegarPeriodoAlocacao(dataInicioHospedagem, dataFimHospedagem);
                    List<Hotel> lista;
                    HotelDAO hotelDAO = new HotelDAO();

                    lista = hotelDAO.getHotel(getApplicationContext());
                    System.out.println(lista.toString());


                    GerenciadorMelhorHotel gerenciadorMelhorHotel = new GerenciadorMelhorHotel();
                    Taxa melhorTaxa = gerenciadorMelhorHotel.pegarMelhorTaxa(tipoCliente, periodo, hoteis);
                    System.out.println("O Hotel mais barato encontrado foi: " + melhorTaxa.getHotel());
                    System.out.println("O seu preco ficou em: " + melhorTaxa.getPreco() + "R$");

                    AlertDialog.Builder alert = new AlertDialog.Builder(BuscaHotel.this);

                    alert.setTitle("Busca Hotel Banco");

                    alert.setMessage("");


                    alert.setTitle("Resultado da Procura");
                    String message = "O Hotel mais barato encontrado foi: " + melhorTaxa.getHotel() + "\n" +
                            "O seu preco ficou em: " + melhorTaxa.getPreco() + "R$";
                    alert.setMessage(message);
                    alerta = alert.create();
                    alerta.show();

                }
            }
        });
    }

    class Connection extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            String result="";
            String host = "http://192.168.0.23/besthotel/hotel.php";

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(host));
                HttpResponse response = client.execute(request);
                BufferedReader reader =  new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer stringBuffer = new StringBuffer("");
                String line ="";
                while((line = reader.readLine())!= null){
                    stringBuffer.append(line);
                    // break;
                }
                reader.close();
                result = stringBuffer.toString();
            } catch (Exception e) {
                return new String("There excpetion: "+ e.getMessage());
            }

            return result;
        }
        @Override
        protected void onPostExecute(String result){
            try {
                JSONObject jsonResult = new JSONObject(result);
                int success = jsonResult.getInt("success");
                if(success == 1){
                    JSONArray hotel = jsonResult.getJSONArray("hotel");
                    hoteis = new ArrayList<Hotel>();
                    for(int i=0; i < hotel.length(); i ++)
                    {
                        JSONObject hote = hotel.getJSONObject(i);
                        int hotelID = hote.getInt("id");
                        String name = hote.getString("nome");
                        int classifi = hote.getInt("classificacao");
                        double price1 = hote.getDouble("precoDiaSemanaRegular");
                        double price2 = hote.getDouble("precoDiaSemanaReward");
                        double price3 = hote.getDouble("precoFimSemanaRegular");
                        double price4 = hote.getDouble("precoFimSemanaReward");
                        String line = name + "-" + classifi + "-" + price1;
                        adapter.add(line);

                        hoteis.add(new Hotel(name, classifi,price1,price2,price3,price4));
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Não tem hoteis", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(),"Não bombo"+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }
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


}
