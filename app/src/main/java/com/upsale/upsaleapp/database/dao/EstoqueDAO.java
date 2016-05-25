package com.upsale.upsaleapp.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.upsale.upsaleapp.database.DatabaseCreator;
import com.upsale.upsaleapp.database.SQLiteHelper;
import com.upsale.upsaleapp.model.Categoria;
import com.upsale.upsaleapp.model.Estoque;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauricio R. Vidal on 23/05/2016.
 */
public class EstoqueDAO {
    private static final String DEBUB = "DEBUG: ";
    private static final String NAME_TABLE = "estoque";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final String[] COLUMS = new String[]{
            "id", "data",
    };
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS estoque";
    private static SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public EstoqueDAO(Context ctx) {
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


    public void salvar(List<Estoque> estoques) {
        if(estoques == null)return;
        for(Estoque e : estoques){
            if(atualizar(e))continue;
            ContentValues values = new ContentValues();
            values.put(COLUMS[0], e.getId());
            values.put(COLUMS[1], sdf.format(e.getData()));
            db.insert(NAME_TABLE, null, values);
        }
        Log.i(DEBUB, "Estoque salva!!");
    }

    private boolean atualizar(Estoque e){
        ContentValues values = new ContentValues();
        values.put(COLUMS[0], e.getId());
        values.put(COLUMS[1], sdf.format(e.getData()));
        return db.update(NAME_TABLE, values,COLUMS[0]+"= ?",new String[]{e.getId()+""}) == 1;

    }


    public List<Estoque> getListaEstoque() {
        Cursor c = getCursor();
        List<Estoque> lista = new ArrayList<>();
        if (c.moveToFirst()) {
            int id = c.getColumnIndex(COLUMS[0]);
            int data = c.getColumnIndex(COLUMS[1]);
            do {
                Estoque e = new Estoque();
                e.setId(c.getLong(id));
                try {
                    e.setData(sdf.parse(c.getString(data)));
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                lista.add(e);
            }while(c.moveToNext());
        }
        return lista;
    }

}
