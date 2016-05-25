package com.upsale.upsaleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    private Animation animAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
    }

    public void startFinancas(View view) {
        view.startAnimation(animAlpha);
        Intent ActivityFinancas = new Intent(this, ficancas.class);
        startActivity(ActivityFinancas);
    }

    public void startGrafico(View view) {
        view.startAnimation(animAlpha);
        Intent ActivityGrafico = new Intent(this, grafico.class);
        startActivity(ActivityGrafico);
    }

    public void startEstoque(View view) {
        view.startAnimation(animAlpha);
        Intent ActivityEstoque = new Intent(this, estoque.class);
        startActivity(ActivityEstoque);
    }

}
