package com.upsale.upsaleapp.activity;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.upsale.upsaleapp.R;
import com.upsale.upsaleapp.database.dao.ItemVendaDAO;

import java.util.Calendar;
import java.util.List;

public class FinanciasActivity extends AppCompatActivity {

    private ListView listView;
    public static int year;
    public static int month;
    public static int day;
    private static TextView tv_data;
    private static TextView tv_data2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financas);
        tv_data = (TextView) findViewById(R.id.tv_data);
        tv_data2 = (TextView) findViewById(R.id.tv_data2);

        ItemVendaDAO iV = new ItemVendaDAO(this);
        List<List<String>> lista = iV.getQuantidadeProdutosPorPeriodo("2016-05-01", "2016-05-05");

        if(lista.size() != 0) {
            listView = (ListView) findViewById(R.id.listView);


        }else{
            display();
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);

            System.out.println(day);
            System.out.println(month);
            System.out.println(year);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            month++;
            tv_data.setText(day + "/" + month + "/" + year);
        }
    }

    public static class DatePickerFragment2 extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);

            System.out.println(day);
            System.out.println(month);
            System.out.println(year);
            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            month++;
            tv_data2.setText(day + "/" + month + "/" + year);
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showDatePickerDialog2(View v) {
        DialogFragment newFragment = new DatePickerFragment2();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    private void display() {
        TextView quantityTextView = (TextView) findViewById(R.id.textView4);
        quantityTextView.setText(quantityTextView.getText() + "\n" + "Não há produtos");
    }

    // public void class exibir (String periodo_inicio, String periodo_fim){

    //}
}
