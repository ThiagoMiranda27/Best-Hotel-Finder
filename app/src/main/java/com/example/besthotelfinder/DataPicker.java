//package com.example.besthotelfinder;
//
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.os.Bundle;
//
//import androidx.fragment.app.DialogFragment;
//import java.util.Calendar;
//
//public class DataPicker extends DialogFragment {
//
//
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        final Calendar c = Calendar.getInstance();
//    int fim_year = c.get(Calendar.YEAR);
//    int fim_month = c.get(Calendar.MONTH);
//    int inicio_day = c.get(Calendar.DAY_OF_MONTH);
//    int inicio_year = c.get(Calendar.YEAR);
//    int inicio_month = c.get(Calendar.MONTH);
//    int inicio_day = c.get(Calendar.DAY_OF_MONTH);
//
//
//
//    final int DATA_INICIO = 0;
//    final int DATA_FIM = 1;
//
//
//
//    protected Dialog onCreateDialog(int id) {
//
//        switch(id){
//            case DATA_INICIO:
//
//                return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), inicio_year, inicio_month, inicio_day);
//            case DATA_FIM:
//                return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), fim_year, fim_month, fim_day);
//        }
//        return null;
//    }
//    }
//}
