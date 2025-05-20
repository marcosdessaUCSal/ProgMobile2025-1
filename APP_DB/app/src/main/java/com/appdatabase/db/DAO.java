package com.appdatabase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.appdatabase.model.Contato;

import java.util.ArrayList;

public class DAO {

    private static DAO instance = null;

    private ArrayList<Contato> contatos;
    private Context ctx;

    private DAO(Context context) {
        this.ctx = context.getApplicationContext();
    }

    public static synchronized DAO getInstance(Context context) {
        if (instance == null) {
            instance = new DAO(context);
        }
        return instance;
    }

    public ArrayList<Contato> getContatos() {
        ArrayList<Contato> lista = new ArrayList<>();
        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(
                    "CONTATO",
                    new String[]{"_id", "NOME", "EMAIL", "TEL"},
                    null, null, null, null, null
            );
            while (cursor.moveToNext()) {
                Integer id = cursor.getInt(0);
                String nome = cursor.getString(1);
                String email = cursor.getString(2);
                String tel = cursor.getString(3);
                lista.add(new Contato(id, nome, email, tel));
            }
            cursor.close();
            db.close();
        } catch (SQLException ex) {
            cursor.close();
            db.close();
            // TODO: TRATAR A EXCEPTION
        }
        return lista;
    }

    private boolean limparTudo() {
        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.delete("CONTATO", null, null);
            db.close();
            return true;
        } catch (SQLException ex) {
            db.close();
            return false;
        }
    }

    public boolean addContato(Contato contato) {
        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NOME", contato.nome);
        cv.put("EMAIL", contato.email);
        cv.put("TEL", contato.tel);
        try {
            db.insert("CONTATO", null, cv);
            db.close();
            return true;
        } catch (SQLException ex) {
            db.close();
            return false;
        }
    }

    public boolean delete(Integer id) {
        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            db.delete("CONTATO", "_id = ?", new String[]{String.valueOf(id)});
            db.close();
        } catch (SQLException ex) {
            db.close();
            return false;
        }

        return true;
    }

    public boolean reset() {
        return limparTudo();
    }


}
