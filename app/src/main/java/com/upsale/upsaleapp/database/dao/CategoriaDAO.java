package com.upsale.upsaleapp.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.upsale.upsaleapp.database.DatabaseCreator;
import com.upsale.upsaleapp.database.SQLiteHelper;
import com.upsale.upsaleapp.model.Categoria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauricio R. Vidal on 23/05/2016.
 */
public class CategoriaDAO extends InterfaceDAO{
    private static final String DEBUB = "DEBUG: ";
    private static final String NAME_TABLE = "categoria";
    private static final String[] COLUMS = new String[]{
            "id", "nome",
    };
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS categoria";
    private static SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public CategoriaDAO(Context ctx) {
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


    public void salvar(List<Categoria> categorias) {
        if(categorias == null)return;
        for(Categoria c : categorias){
            if(atualizar(c))continue;
            ContentValues values = new ContentValues();
            values.put(COLUMS[0], c.getId());
            values.put(COLUMS[1], c.getNome());
            db.insert(NAME_TABLE, null, values);
        }
        Log.i(DEBUB, "Categoria salva!!");
    }

    private boolean atualizar(Categoria c){
        ContentValues values = new ContentValues();
        values.put(COLUMS[0], c.getId());
        values.put(COLUMS[1], c.getNome());
        return db.update(NAME_TABLE, values,COLUMS[0]+"= ?",new String[]{c.getId()+""}) == 1;

    }


    public List<Categoria> getListaCategoria() {
        Cursor c = getCursor();
        List<Categoria> lista = new ArrayList<>();
        if (c.moveToFirst()) {
            int id = c.getColumnIndex(COLUMS[0]);
            int nome = c.getColumnIndex(COLUMS[1]);
            do {
                Categoria categoria = new Categoria();
                categoria.setId(c.getLong(id));
                categoria.setNome(c.getString(nome));
                lista.add(categoria);
            }while(c.moveToNext());
        }
        return lista;
    }

}
