package com.upsale.upsaleapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.upsale.upsaleapp.R;
import com.upsale.upsaleapp.ws.WSUpSale;

public class PrimeiraTelaActivity extends Activity {

    private EditText editLogin;
    private EditText editSenha;
    private Button buttonLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira_tela);
        editLogin = (EditText) findViewById(R.id.login);
        editSenha = (EditText) findViewById(R.id.senha);
        buttonLogar = (Button) findViewById(R.id.button);
        buttonLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editLogin.getText().toString();
                String senha = editSenha.getText().toString();

                long id = WSUpSale.autenticar(login, senha);


            }
        });
    }
}
