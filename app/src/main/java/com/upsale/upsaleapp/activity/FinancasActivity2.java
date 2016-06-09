package com.upsale.upsaleapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.upsale.upsaleapp.R;
import com.upsale.upsaleapp.database.dao.ItemVendaDAO;
import com.upsale.upsaleapp.utils.Dialogs;

import java.text.NumberFormat;
import java.util.List;

public class FinancasActivity2 extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<List<String>> adapter;
    private TextView total;
    private List<List<String>> lista;
    private String dataInicio;
    private String dataFim;
    private double totalVenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financas2);
        listView = (ListView) findViewById(R.id.listView);

        ItemVendaDAO iV = new ItemVendaDAO(this);
        Bundle params = this.getIntent().getExtras();

        dataInicio = params.getString("dataInicio");
        dataFim = params.getString("dataFim");
        total = (TextView) findViewById(R.id.total);

        lista = iV.getQuantidadeProdutosPorPeriodo(dataInicio, dataFim);

        if (lista.size() != 0){
            listView.setAdapter(carregarListView());
            total.setText("Total: " + NumberFormat.getCurrencyInstance().format(totalVenda));
        }else{
            noProductsMessage(params.getString("dataInicio"), params.getString("dataFim"));
            //Intent ActivityFinancas = new Intent(FinancasActivity2.this, FinancasActivity.class);
            //startActivity(ActivityFinancas);
        }


    }

    private ArrayAdapter<List<String>> carregarListView(){
        final LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        adapter = new ArrayAdapter<List<String>>(getApplicationContext(), R.layout.item_itemvenda){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = inflater.inflate(R.layout.item_itemvenda, parent, false);
                }
                TextView nome = (TextView) convertView.findViewById(R.id.editNome);
                TextView subtotal = (TextView) convertView.findViewById(R.id.editSubtotal);
                List<String> lista2 =  getItem(position);

                nome.setText(lista2.get(0).toString());
                subtotal.setText(lista2.get(1).toString());


                return convertView;
            }
        };
        for(List<String> ls : lista){
            adapter.add(ls);
            totalVenda += Double.parseDouble(ls.get(2));
        }
        return adapter;
    }

    public void noProductsMessage(String inicio, String fim) {
        listView.setVisibility(View.INVISIBLE);
        total.setVisibility(View.INVISIBLE);
        Dialogs.showDialog(this, "Erro", "Nesse periodo: " + inicio + " a " + fim +  " não houve vendas");
    }
}
