package com.upsale.upsaleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.upsale.upsaleapp.model.Categoria;
import com.upsale.upsaleapp.ws.WSUpSale;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startFinancas(View view) {
        Intent ActivityFinancas = new Intent(this, ficancas.class);
        startActivity(ActivityFinancas);
    }

    public void startGrafico(View view) {
        Intent ActivityGrafico = new Intent(this, grafico.class);
        startActivity(ActivityGrafico);
    }

    public void startEstoque(View view) {
        Intent ActivityEstoque = new Intent(this, estoque.class);
        startActivity(ActivityEstoque);
    }

}
