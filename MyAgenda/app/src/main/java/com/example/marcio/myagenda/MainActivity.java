package com.example.marcio.myagenda;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.marcio.myagenda.adapter.CustonAdapter;
import com.example.marcio.myagenda.helper.BancoHelper;
import com.example.marcio.myagenda.model.Contatos;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BancoHelper bh;
    private ListView listView;
    private List<Contatos> contatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lista);

        bh = new BancoHelper(getBaseContext());

        contatos = bh.getAllContatos();

        final CustonAdapter custonAdapter = new CustonAdapter(contatos, getApplicationContext()
        );
        listView.setAdapter(custonAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contatos contatos1 = new Contatos();
                contatos1.setNome("Marcio" + contatos.size());
                contatos1.setTelefone("9667-2922");
                contatos1.setPhoto(R.drawable.faceavatar);
                bh.addContato(contatos1);

                contatos.add(contatos1);

                CustonAdapter custonAdapter1 = new CustonAdapter(contatos, getApplicationContext());
                listView.setAdapter(custonAdapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Função de deletar
                contatos.get(position).setPhoto(R.drawable.imagem2);
                //contatos.get(position).setPhoto(R.drawable.usuario1);
                //contatos.get(position).setPhoto(R.drawable.usuario2);
                //contatos.get(position).setPhoto(R.drawable.usuario3);
                //contatos.get(position).setPhoto(R.drawable.vakinha);
                //Usando função de update
                bh.updateContato(contatos.get(position));
                atualizaLista();
            }
        });
    }

    private void atualizaLista(){
        contatos = bh.getAllContatos();

        CustonAdapter custonAdapter = new CustonAdapter(contatos, getApplicationContext());
        listView.setAdapter(custonAdapter);
    }

}
