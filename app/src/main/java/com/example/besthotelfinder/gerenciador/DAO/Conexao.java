package com.example.besthotelfinder.gerenciador.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "besthotel";
    private static final int VERSAO_BANCO = 2;

    public Conexao(Context contexto) {
        super(contexto, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS Hotel ( id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "nome VARCHAR(255), classificacao int(2), precoDiaSemanaRegular double(5,2), " +
                        "precoDiaSemanaReward double(5,2), precoFimSemanaRegular double(5,2), precoFimSemanaReward double(5,2));"
        );
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int antiga, int nova) {

    }
}