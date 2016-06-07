package com.upsale.upsaleapp.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.upsale.upsaleapp.R;
import com.upsale.upsaleapp.database.dao.ItemVendaDAO;

import java.util.ArrayList;
import java.util.List;

public class GraficoActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico2);
        ItemVendaDAO iV = new ItemVendaDAO(this);
        Bundle params = this.getIntent().getExtras();
        String dataInicio = null;
        String dataFim = null;
        if(params!=null) {
            dataInicio = formatDate(params.getString("dataInicio"));
            dataFim = formatDate(params.getString("dataFim"));
        }
        //List<List<String>> lista = iV.getQuantidadeProdutosPorCategoriaPorPeriodo("2016-05-01", "2016-05-05");
        List<List<String>> lista = iV.getQuantidadeProdutosPorCategoriaPorPeriodo(dataInicio, dataFim);
        if(lista.size() != 0) {
            HorizontalBarChart chart = (HorizontalBarChart) findViewById(R.id.chart);
            BarData data = new BarData(getXAxisValues(lista), getDataSet(lista));
            chart.setData(data);
            chart.setDescription("");
            chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            chart.animateXY(2000, 2000);
            chart.invalidate();
        }else{
            noProductsMessage(params.getString("dataInicio"), params.getString("dataFim"));
        }
    }

    private String formatDate(String date){
        String [] aux = date.split("/");
        String newDate = aux[2] + "-";
        if(Integer.parseInt(aux[1]) < 10){
            newDate += "0";
        }
        newDate += aux[1] + "-";
        if(Integer.parseInt(aux[0]) < 10){
            newDate += "0";
        }
        newDate += aux[0];
        return newDate;
    }

    private ArrayList<IBarDataSet> getDataSet(List<List<String>> lista) {
        ArrayList<IBarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        for (int i=0; i< lista.size(); i++){
            valueSet1.add(new BarEntry(Float.parseFloat(lista.get(i).get(1)),i));
        }

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Quantidade de produtos por categoria");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
//        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
//        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        //dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues(List<List<String>> lista) {
        ArrayList<String> xAxis = new ArrayList<>();
        for(List<String> l : lista){
            xAxis.add(l.get(0));
        }

        return xAxis;
    }

    public void noProductsMessage(String inicio, String fim) {
        TextView textView = (TextView) findViewById(
                R.id.textView4);
        HorizontalBarChart chart = (HorizontalBarChart) findViewById(R.id.chart);
        chart.setVisibility(View.INVISIBLE);

        //textView.setText(quantityTextView.getText() + "\n" + "Não há produtos");
        String message = "Não há categorias de produtos mais vendidos entre as datas " + inicio + " e " + fim + ".\n";
        message += "Por favor tente outro período.";
        android.view.ViewGroup.LayoutParams params = textView.getLayoutParams();
        params.height = ActionBar.LayoutParams.MATCH_PARENT;
        textView.setLayoutParams(params);
        textView.setText(message);
    }
}
