package com.example.marcio.myagenda.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.marcio.myagenda.model.Contatos;

/**
 * Created by marcio on 06/02/2016.
 */
public class Banco extends SQLiteOpenHelper {
    private SQLiteOpenHelper db;

    public Banco(Context context, String nome, SQLiteDatabase.CursorFactory factory, int version){
        super(context, nome, factory, version);
    }

    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE contacts(_id INTEGER PRIMARY KEY AUTOINCREMENT, nome, TEXT, telefone TEXT, photo integer)";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "DROP TABLE IF EXISTS contacts";
        db.execSQL(sql);
    }

    public Cursor buscar(String sql){
        return getWritableDatabase().rawQuery(sql, null);
    }

    public int updateContatos(Contatos contatos){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", contatos.getNome());
        values.put("telefone", contatos.getTelefone());

        return db.update("contacts", values, "_id = ?", new String[]{
                String.valueOf(contatos.get_id())});
    }

    public void deleteContato(Contatos contatos){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("contacts", "_id = ?", new String[]{String.valueOf(contatos.get_id())});
        db.close();
    }
}
