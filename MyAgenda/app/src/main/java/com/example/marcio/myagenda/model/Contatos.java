package com.example.marcio.myagenda.model;

import java.io.Serializable;

/**
 * Created by marcio on 06/02/2016.
 */
public class Contatos implements Serializable{
    private String nome;
    private String telefone;
    private int photo;
    private int _id;

    public Contatos(){

    }

    public Contatos(String nome, String telefone, int photo){
        this.nome = nome;
        this.telefone = telefone;
        this.photo = photo;
    }

    public Contatos (int _id, String nome, String telefone, int photo){
        this.nome = nome;
        this.telefone = telefone;
        this.photo = photo;
        this._id = _id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
