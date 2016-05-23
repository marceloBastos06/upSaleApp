package com.upsale.upsaleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.upsale.upsaleapp.model.Categoria;
import com.upsale.upsaleapp.ws.WSUpSale;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Categoria> categorias = WSUpSale.getCategorias();
        if(categorias != null) {
            for (Categoria c : categorias) {
                System.out.println("DEBUG: " + c.getNome());
            }
        }
    }
}
