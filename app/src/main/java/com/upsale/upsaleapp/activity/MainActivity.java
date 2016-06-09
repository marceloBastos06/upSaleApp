package com.upsale.upsaleapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.upsale.upsaleapp.R;

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
        Intent ActivityFinancas = new Intent(this, FinancasActivity.class);
        startActivity(ActivityFinancas);
    }

    public void startGrafico(View view) {
        view.startAnimation(animAlpha);
        Intent ActivityGrafico = new Intent(this, GraficoActivity.class);
        startActivity(ActivityGrafico);
    }

    public void startEstoque(View view) {
        view.startAnimation(animAlpha);
        Intent ActivityEstoque = new Intent(this, EstoqueActivity.class);
        startActivity(ActivityEstoque);
    }

}
