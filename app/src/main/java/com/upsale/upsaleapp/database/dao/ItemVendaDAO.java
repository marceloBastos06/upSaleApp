package com.upsale.upsaleapp.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.upsale.upsaleapp.database.DatabaseCreator;
import com.upsale.upsaleapp.database.SQLiteHelper;
import com.upsale.upsaleapp.model.ItemVenda;
import com.upsale.upsaleapp.model.Venda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Mauricio R. Vidal on 23/05/2016.
 */
public class ItemVendaDAO extends InterfaceDAO{
    private static final String DEBUB = "DEBUG: ";
    private static final String NAME_TABLE = "item_venda";
    private static final String[] COLUMS = new String[]{
            "id_produto","id_venda", "quantidade",
    };
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS item_venda";
    private static SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public ItemVendaDAO(Context ctx) {
        dbHelper = SQLiteHelper.getInstance(ctx, DatabaseCreator.NAME_DATABASE, DatabaseCreator.VERSION,
                DatabaseCreator.SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DELETE);
        //abre o banco no mode escrita para poder alterar tbm
        db = dbHelper.getWritableDatabase();

    }

    public void closeDataBase() {
        if (db != null) {
            db.close();
        }
    }

    private Cursor getCursor() {
        return db.query(NAME_TABLE, COLUMS, null, null, null, null, null, null);
    }


    public void salvar(List<ItemVenda> item_vendas) {
        if(item_vendas == null)return;
        for(ItemVenda v : item_vendas){
            if(atualizar(v))continue;
            ContentValues values = new ContentValues();
            values.put(COLUMS[0], v.getId_produto());
            values.put(COLUMS[1], v.getId_venda());
            values.put(COLUMS[2], v.getQuantidade());
            db.insert(NAME_TABLE, null, values);
        }
        Log.i(DEBUB, "Venda salva!!");
    }

    private boolean atualizar(ItemVenda v){
        ContentValues values = new ContentValues();
        values.put(COLUMS[0], v.getId_produto());
        values.put(COLUMS[1], v.getId_venda());
        values.put(COLUMS[2], v.getQuantidade());
        return db.update(NAME_TABLE, values,COLUMS[0]+"= ? AND "+ COLUMS[1] + "= ?",new String[]{v.getId_produto()+"", v.getId_venda()+""}) == 1;

    }


    public List<ItemVenda> getListaItemVenda() {
        Cursor c = getCursor();
        List<ItemVenda> lista = new ArrayList<>();
        if (c.moveToFirst()) {
            int id_produto = c.getColumnIndex(COLUMS[0]);
            int id_venda = c.getColumnIndex(COLUMS[1]);
            int quantidade = c.getColumnIndex(COLUMS[2]);
            do {
                ItemVenda v = new ItemVenda();
                v.setId_produto(c.getLong(id_produto));
                v.setId_venda(c.getLong(id_venda));
                v.setQuantidade(c.getInt(quantidade));
                lista.add(v);
            }while(c.moveToNext());
        }
        return lista;
    }

//    public int getQuantidadeProdutosPorCategoria(String categoria){
//        Cursor c = db.rawQuery("SELECT sum(i.quantidade) as qtdTotal FROM categoria as c, produto as p, item_venda as i " +
//                "WHERE c.id = p.id_categoria and p.id = i.id_produto and c.nome = ?", new String[]{categoria});
//
//        int qtd =0;
//        if (c.moveToFirst()) {
//            qtd = c.getInt(c.getColumnIndex("qtdTotal"));
//        }
//
//        return qtd;
//    }

    public List<List<String>> getQuantidadeProdutosPorCategoriaPorPeriodo(String inicioPeriodo, String fimPeriodo){
        Cursor cursor = db.rawQuery("SELECT c.nome as nomeCategoria, sum(i.quantidade) as qtdTotal , v.data as data" +
                " FROM categoria as c, produto as p, item_venda as i, venda as v" +
                " WHERE c.id = p.id_categoria and p.id = i.id_produto and v.id = i.id_venda and" +
                " v.data >= ? and v.data <= ?" +
                " GROUP BY c.id ORDER BY qtdTotal ASC", new String[]{inicioPeriodo, fimPeriodo});

        List<List<String>> lista = new ArrayList<>();
//        ArrayList<String> a = new ArrayList<>();
//        a.add("Limpeza");
//        a.add("60");
//        a.add("2016-01-03");
//        lista.add(a);
//        a = new ArrayList<>();
//        a.add("Eletrônico");
//        a.add("30");
//        a.add("2016-01-03");
//        lista.add(a);
        if (cursor.moveToFirst()) {
//            a.add("Teste");
//            a.add("Teste2");
//            lista.add(a);
            int categogia =cursor.getColumnIndex("nomeCategoria");
            int qtd = cursor.getColumnIndex("qtdTotal");
            int data = cursor.getColumnIndex("data");
            do {
                List<String> l = new ArrayList<>();
                l.add(cursor.getString(categogia));
                l.add("" + cursor.getInt(qtd));
                l.add(cursor.getString(data));
                lista.add(l);
            }while(cursor.moveToNext());
        }

//        for (int i = lista.size() -1 ; i>=0 ; i--){
//            String [] aux = lista.get(i).get(2).split("/");
//            Calendar date = new GregorianCalendar(Integer.parseInt(aux[2]), Integer.parseInt(aux[1]), Integer.parseInt(aux[0]));
//
//            if(inicioPeriodo.after(date) || fimPeriodo.before(date)){
//                lista.remove(i);
//            }
//        }

        return lista;
    }
}
