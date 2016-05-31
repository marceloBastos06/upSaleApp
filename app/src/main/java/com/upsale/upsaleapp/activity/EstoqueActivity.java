package com.upsale.upsaleapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.upsale.upsaleapp.R;
import com.upsale.upsaleapp.adapter.ItemEstoqueAdapter;
import com.upsale.upsaleapp.database.dao.ItemEstoqueDAO;
import com.upsale.upsaleapp.model.ItemEstoque;

public class EstoqueActivity extends AppCompatActivity {

    private ListView listView;
    private ItemEstoqueDAO daoItemEstoque;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);
        listView = (ListView) findViewById(R.id.listView);
        daoItemEstoque = new ItemEstoqueDAO(this);
        listView.setAdapter(carregarListView());
    }

    private ArrayAdapter<ItemEstoque> carregarListView(){
        ItemEstoqueAdapter adapter = new ItemEstoqueAdapter(getApplicationContext());
        for(ItemEstoque ie : daoItemEstoque.getListaItemEstoque()){
            adapter.add(ie);
        }
        return adapter;
    }

}
