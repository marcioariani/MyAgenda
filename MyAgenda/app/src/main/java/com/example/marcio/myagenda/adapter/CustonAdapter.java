package com.example.marcio.myagenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcio.myagenda.R;
import com.example.marcio.myagenda.model.Contatos;

import java.util.List;

/**
 * Created by marcio on 06/02/2016.
 */
public class CustonAdapter extends BaseAdapter{
    private LayoutInflater layoutInflater;
    private List<Contatos> contatos;

    public CustonAdapter(List<Contatos> contatos, Context context){
        this.contatos = contatos;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return contatos.size();
    }

    @Override
    public Object getItem(int position){
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Contatos contato = contatos.get(position);
        convertView = layoutInflater.inflate(R.layout.contatos_layout, null);
        ((ImageView)convertView.findViewById(R.id.photo)).setImageResource(contato.getPhoto());
        ((TextView)convertView.findViewById(R.id.tvNome)).setText(contato.getNome());
        ((TextView)convertView.findViewById(R.id.tvTelefone)).setText(contato.getTelefone());

        return convertView;
    }
}
