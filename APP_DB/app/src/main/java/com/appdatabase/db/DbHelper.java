package com.appdatabase.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NOME = "inteligentes_db";
    private static final int DB_VERSAO = 1;
    public DbHelper(@Nullable Context context) {
        super(context, DB_NOME, null, DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        criarBaseDeDados(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Para atualizar a versão do banco de dados. Neste exercício, é desnecessário.
    }

    private void criarBaseDeDados(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("CREATE TABLE CONTATO (")
                .append("   _id INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append("   NOME TEXT,")
                .append("   EMAIL TEXT,")
                .append("   TEL TEXT")
                .append(")");
        db.execSQL(sb.toString());
    }
}
