package com.example.besthotelfinder.gerenciador.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.besthotelfinder.gerenciador.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelDAO {


    public static void inserir(Context contexto, Hotel hotel){

        Conexao conexao = new Conexao(contexto);
        SQLiteDatabase banco = conexao.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", hotel.getNome());
        valores.put("classificacao", hotel.getClassificacao());
        valores.put("precoDiaSemanaRegular", hotel.getPrecoDiaSemanaRegular());
        valores.put("precoDiaSemanaReward", hotel.getPrecoDiaSemanaReward());
        valores.put("precoFimSemanaRegular", hotel.getPrecoFimSemanaRegular());
        valores.put("precoFimSemanaReward", hotel.getPrecoFimSemanaReward());

        banco.insert("Hotel", null, valores);

    }

    public static List<Hotel> getHotel(Context contexto){

        List<Hotel> listaHotel = new ArrayList<>();
        Conexao conexao = new Conexao(contexto);
        SQLiteDatabase banco = conexao.getReadableDatabase();

        String sql = "SELECT * FROM Hotel";


        Cursor tabela = banco.rawQuery(sql, null);

        if(tabela.getCount() > 0){
            tabela.moveToFirst();
            do{
                Hotel hotel = new Hotel();
                hotel.setId( tabela.getInt(0));
                hotel.setNome(tabela.getString( 1));
                hotel.setClassificacao(tabela.getInt(3));
                hotel.setPrecoDiaSemanaRegular(tabela.getDouble(4));
                hotel.setPrecoDiaSemanaReward(tabela.getDouble(5));
                hotel.setPrecoFimSemanaRegular(tabela.getDouble(6));
                hotel.setPrecoFimSemanaReward(tabela.getDouble(7));
            }while( tabela.moveToNext());


        }
        return listaHotel;
    }
}
