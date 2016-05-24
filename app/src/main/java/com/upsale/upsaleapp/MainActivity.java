package com.upsale.upsaleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.widget.Button;
import android.widget.Toast;
=======
>>>>>>> f7df257a9a2784f8497ae25cac3b257619488296

import com.upsale.upsaleapp.activity.PrimeiraTelaActivity;
import com.upsale.upsaleapp.database.dao.UsuarioDAO;
import com.upsale.upsaleapp.model.Categoria;
import com.upsale.upsaleapp.ws.WSUpSale;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button = (Button) findViewById(R.id.estoque);
        Toast.makeText(getApplicationContext(), new UsuarioDAO(getApplicationContext()).getUsuario()+"", Toast.LENGTH_LONG).show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, PrimeiraTelaActivity.class);
                startActivity(it);
            }
        });
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
