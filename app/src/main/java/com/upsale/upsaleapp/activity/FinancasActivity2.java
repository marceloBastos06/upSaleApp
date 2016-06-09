package com.upsale.upsaleapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.upsale.upsaleapp.R;
import com.upsale.upsaleapp.database.dao.ItemVendaDAO;

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

        lista = iV.getQuantidadeProdutosPorPeriodo(dataInicio, dataFim);

        listView.setAdapter(carregarListView());
        total = (TextView) findViewById(R.id.total);


        total.setText("Total: " + NumberFormat.getCurrencyInstance().format(totalVenda));
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

}
