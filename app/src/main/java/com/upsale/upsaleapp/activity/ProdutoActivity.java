package com.upsale.upsaleapp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.upsale.upsaleapp.R;
import com.upsale.upsaleapp.model.Produto;

import java.text.NumberFormat;

public class ProdutoActivity extends AppCompatActivity {

    private TextView nome;
    private TextView preco;
    private TextView descricao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        Bundle bundle = getIntent().getExtras();
        nome = (TextView) findViewById(R.id.editNome);
        preco = (TextView) findViewById(R.id.editPreco);
        descricao = (TextView) findViewById(R.id.editDescricao);
        Produto p = (Produto) bundle.get("produto");
        nome.setText(p.getNome());
        preco.setText("Preço: "+ NumberFormat.getCurrencyInstance().format(p.getPreco()));
        descricao.setText(p.getDescricao());

    }

}
