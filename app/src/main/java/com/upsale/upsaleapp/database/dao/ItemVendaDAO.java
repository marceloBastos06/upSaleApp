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
import java.util.List;

/**
 * Created by Mauricio R. Vidal on 23/05/2016.
 */
public class ItemVendaDAO {
    private static final String DEBUB = "DEBUG: ";
    private static final String NAME_TABLE = "item_venda";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
}
