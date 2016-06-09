package com.upsale.upsaleapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.upsale.upsaleapp.R;
import com.upsale.upsaleapp.adapter.ItemEstoqueAdapter;
import com.upsale.upsaleapp.database.dao.ItemEstoqueDAO;
import com.upsale.upsaleapp.database.dao.ProdutoDAO;
import com.upsale.upsaleapp.model.ItemEstoque;
import com.upsale.upsaleapp.model.Produto;

public class EstoqueActivity extends AppCompatActivity {

    private ListView listView;
    private ItemEstoqueDAO daoItemEstoque;
    private ProdutoDAO daoProduto;
    private ItemEstoqueAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        listView = (ListView) findViewById(R.id.listView);

        daoItemEstoque = new ItemEstoqueDAO(this);
        daoProduto = new ProdutoDAO(this);

        listView.setAdapter(carregarListView());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemEstoque ie = adapter.getItem(position);
                Produto p = daoProduto.getProdutoPorId(ie.getId_produto());
                Intent it = new Intent(EstoqueActivity.this, ProdutoActivity.class);
                it.putExtra("produto", p);
                startActivity(it);
            }
        });
    }

    private ArrayAdapter<ItemEstoque> carregarListView(){
        adapter = new ItemEstoqueAdapter(getApplicationContext());
        for(ItemEstoque ie : daoItemEstoque.getListaItemEstoque()){
            adapter.add(ie);
        }
        return adapter;
    }

}
