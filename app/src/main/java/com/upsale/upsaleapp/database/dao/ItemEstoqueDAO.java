package com.upsale.upsaleapp.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.upsale.upsaleapp.database.DatabaseCreator;
import com.upsale.upsaleapp.database.SQLiteHelper;
import com.upsale.upsaleapp.model.ItemEstoque;
import com.upsale.upsaleapp.model.ItemVenda;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauricio R. Vidal on 23/05/2016.
 */
public class ItemEstoqueDAO  extends InterfaceDAO{
    private static final String DEBUB = "DEBUG: ";
    private static final String NAME_TABLE = "item_estoque";
    private static final String[] COLUMS = new String[]{
            "id_produto","id_estoque", "quantidade","quantidade_maxima",
    };
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS item_estoque";
    private static SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public ItemEstoqueDAO(Context ctx) {
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


    public void salvar(List<ItemEstoque> itemEstoques) {
        if(itemEstoques == null)return;
        for(ItemEstoque ie : itemEstoques){
            if(atualizar(ie))continue;
            ContentValues values = new ContentValues();
            values.put(COLUMS[0], ie.getId_produto());
            values.put(COLUMS[1], ie.getId_estoque());
            values.put(COLUMS[2], ie.getQuantidade());
            values.put(COLUMS[3], ie.getQuantidadeMaxima());
            db.insert(NAME_TABLE, null, values);
        }
        Log.i(DEBUB, "ItemEstoque salvo!!");
    }

    private boolean atualizar(ItemEstoque ie){
        ContentValues values = new ContentValues();
        values.put(COLUMS[0], ie.getId_produto());
        values.put(COLUMS[1], ie.getId_estoque());
        values.put(COLUMS[2], ie.getQuantidade());
        values.put(COLUMS[3], ie.getQuantidadeMaxima());
        return db.update(NAME_TABLE, values,COLUMS[0]+"= ? AND "+ COLUMS[1] + "= ?",new String[]{ie.getId_produto()+"", ie.getId_estoque()+""}) == 1;

    }


    public List<ItemEstoque> getListaItemEstoque() {
        Cursor c = getCursor();
        List<ItemEstoque> lista = new ArrayList<>();
        if (c.moveToFirst()) {
            int id_produto = c.getColumnIndex(COLUMS[0]);
            int id_estoque = c.getColumnIndex(COLUMS[1]);
            int quantidade = c.getColumnIndex(COLUMS[2]);
            int quantidade_maxima = c.getColumnIndex(COLUMS[3]);
            do {
                ItemEstoque v = new ItemEstoque();
                v.setId_produto(c.getLong(id_produto));
                v.setId_estoque(c.getLong(id_estoque));
                v.setQuantidade(c.getInt(quantidade));
                v.setQuantidadeMaxima(c.getInt(quantidade_maxima));
                lista.add(v);
            }while(c.moveToNext());
        }
        return lista;
    }


}
