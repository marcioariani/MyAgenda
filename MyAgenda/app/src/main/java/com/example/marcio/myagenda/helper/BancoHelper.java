package com.example.marcio.myagenda.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.marcio.myagenda.model.Contatos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcio on 06/02/2016.
 */
public class BancoHelper  extends SQLiteOpenHelper{

    //Versão do Banco
    private static final int DATABASE_VERSION = 1;

    //Nome da Base de dados
    private static final String DATABASE_NAME = "central";

    //Nome da tabela
    private static final String TABLE_NAME = "contacts";

    public BancoHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Inicio da criação da Tabela
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE contacts(_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, telefone TEXT, photo integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Comando para deletar a tabela já existente
        db.execSQL("DROP TABLE IF EXISTS contacts");
        //Criando uma nova tabela contacts
        onCreate(db);
    }

    //Adicionando um novo contato
    public void addContato(Contatos contatos){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", contatos.getNome());
        values.put("telefone", contatos.getTelefone());
        values.put("photo", contatos.getPhoto());

        //Inserindo valor
        db.insert(TABLE_NAME, null, values);

        //Fechando a conexão com o banco de dados
        db.close();
    }

    //Retorna um único contato
    public Contatos getContatos(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT _id, nome, telefone, photo FROM contacts where _id = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if(cursor != null){
            cursor.moveToFirst();

            Contatos contatos = new Contatos();
            contatos.set_id(cursor.getInt(0));
            contatos.setNome(cursor.getString(1));
            contatos.setTelefone(cursor.getString(2));
            contatos.setTelefone(cursor.getString(3));
            contatos.setPhoto(cursor.getInt(4));

            //return contato
            return contatos;

        }

        return null;

    }

    //Obtendo todos os contatos
    public List<Contatos> getAllContatos(){
        List <Contatos> contatos = new ArrayList<>();
        //SQL da consulta da base de dados
        String sql = "SELECT _id, nome, telefone, photo FROM contacts";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        //Looping povoando a lista de contatos
        if(cursor.moveToFirst()){
            do{
                Contatos contatos1 = new Contatos();
                contatos1.set_id(cursor.getInt(0));
                contatos1.setNome(cursor.getString(1));
                contatos1.setTelefone(cursor.getString(2));
                contatos1.setPhoto(cursor.getInt(3));

                //Adicionando elemento na lista
                contatos.add(contatos1);

            } while (cursor.moveToNext());
        }

        return contatos;
    }

    //Updating um único contato
    public int updateContato(Contatos contatos){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", contatos.getNome());
        values.put("telefone", contatos.getTelefone());
        values.put("photo", contatos.getPhoto());

        //updating row
        return db.update(TABLE_NAME, values, "_id = ?", new String[]{String.valueOf(contatos.get_id())});
    }

    //Deletando um contato
    public void deleteContato(Contatos contatos){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "_id = ?", new String[]{String.valueOf(contatos.get_id())});
        db.close();
    }

    //Obtendo quantidade de contatos
    public int getContatosCount(){
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

}
