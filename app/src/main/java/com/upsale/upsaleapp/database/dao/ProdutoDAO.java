package com.upsale.upsaleapp.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.upsale.upsaleapp.database.DatabaseCreator;
import com.upsale.upsaleapp.database.SQLiteHelper;
import com.upsale.upsaleapp.model.Categoria;
import com.upsale.upsaleapp.model.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauricio R. Vidal on 24/05/2016.
 */
public class ProdutoDAO {
    private static final String DEBUB = "DEBUG: ";
    private static final String NAME_TABLE = "produto";
    private static final String[] COLUMS = new String[]{
            "id", "id_usuario","id_categoria", "nome","descricao", "preco", "lucro",
    };
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS produto";
    private static SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public ProdutoDAO(Context ctx) {
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

    public void salvar(List<Produto> produtos) {
        if(produtos == null)return;
        for(Produto p : produtos){
            if(atualizar(p))continue;
            ContentValues values = new ContentValues();
            values.put(COLUMS[0], p.getId());
            values.put(COLUMS[1], p.getId_usuario());
            values.put(COLUMS[2], p.getId_categoria());
            values.put(COLUMS[3], p.getNome());
            values.put(COLUMS[4], p.getDescricao());
            values.put(COLUMS[5], p.getPreco());
            values.put(COLUMS[6], p.getLucro());
            db.insert(NAME_TABLE, null, values);
        }
        Log.i(DEBUB, "Produto salvo!!");
    }

    private boolean atualizar(Produto p){
        ContentValues values = new ContentValues();
        values.put(COLUMS[0], p.getId());
        values.put(COLUMS[1], p.getId_usuario());
        values.put(COLUMS[2], p.getId_categoria());
        values.put(COLUMS[3], p.getNome());
        values.put(COLUMS[4], p.getDescricao());
        values.put(COLUMS[5], p.getPreco());
        values.put(COLUMS[6], p.getLucro());
        return db.update(NAME_TABLE, values,COLUMS[0]+"= ?",new String[]{p.getId()+""}) == 1;

    }


    public List<Produto> getListaProduto() {
        Cursor c = getCursor();
        List<Produto> lista = new ArrayList<>();
        if (c.moveToFirst()) {
            int id = c.getColumnIndex(COLUMS[0]);
            int id_usuario = c.getColumnIndex(COLUMS[1]);
            int id_categoria = c.getColumnIndex(COLUMS[2]);
            int nome = c.getColumnIndex(COLUMS[3]);
            int descricao = c.getColumnIndex(COLUMS[4]);
            int preco = c.getColumnIndex(COLUMS[5]);
            int lucro = c.getColumnIndex(COLUMS[6]);
            do {
                Produto produto = new Produto();
                produto.setId(c.getLong(id));
                produto.setNome(c.getString(nome));
                produto.setId_usuario(c.getLong(id_usuario));
                produto.setId_categoria(c.getLong(id_categoria));
                produto.setDescricao(c.getString(descricao));
                produto.setLucro(c.getFloat(lucro));
                produto.setPreco(c.getFloat(preco));
                lista.add(produto);
            }while(c.moveToNext());
        }
        return lista;
    }


    public Produto getProdutoPorId(long idProduto){
        Cursor c = db.query(NAME_TABLE, COLUMS, COLUMS[0] + "="+ idProduto, null, null, null, null, null);
        if (c.moveToFirst()) {
            int id = c.getColumnIndex(COLUMS[0]);
            int id_usuario = c.getColumnIndex(COLUMS[1]);
            int id_categoria = c.getColumnIndex(COLUMS[2]);
            int nome = c.getColumnIndex(COLUMS[3]);
            int descricao = c.getColumnIndex(COLUMS[4]);
            int preco = c.getColumnIndex(COLUMS[5]);
            int lucro = c.getColumnIndex(COLUMS[6]);
            Produto produto = new Produto();
            produto.setId(c.getLong(id));
            produto.setNome(c.getString(nome));
            produto.setId_usuario(c.getLong(id_usuario));
            produto.setId_categoria(c.getLong(id_categoria));
            produto.setDescricao(c.getString(descricao));
            produto.setLucro(c.getFloat(lucro));
            produto.setPreco(c.getFloat(preco));
            return produto;
        }
        return null;

    }


}
