package com.example.besthotelfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Calendar;

public class BuscaHotel extends AppCompatActivity {

    Calendar calendar_dataAtual;
    TextView tv_DataAtual;
    EditText et_DateInicio, et_DateFim;
    Button btn_Enviar;
    RadioButton rb_vip, rb_regular;

    static final int DATE_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_hotel);

        rb_vip = findViewById(R.id.rb_vip);
        rb_regular = findViewById(R.id.rb_regular);

        et_DateInicio = findViewById(R.id.et_DateInicio);

        final Calendar dataInicio;
        final Calendar dataFim;
//        Button btnEntrada = (Button) findViewById(R.id.btn_DataEntrada);
//        Button btnSaida = (Button) findViewById(R.id.btn_DataSaida);
        final TextView tv_Entrada = (TextView) findViewById(R.id.tv_dataEntrada);
        final TextView tv_Saida = (TextView) findViewById(R.id.tv_dataSaida);

        dataInicio = Calendar.getInstance();

        et_DateInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mostrarData(tv_Entrada, dataInicio);
            }
        };

        dataFim = Calendar.getInstance();

        et_DateFim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarData(tv_Saida, dataFim);
            }
        });
        atualizar(tv_Entrada, dataInicio);
        atualizar(tv_Saida, dataFim);
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
