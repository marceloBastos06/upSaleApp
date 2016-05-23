package com.upsale.upsaleapp.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.upsale.upsaleapp.database.DatabaseCreator;
import com.upsale.upsaleapp.database.SQLiteHelper;
import com.upsale.upsaleapp.model.Usuario;


public class UsuarioDAO {
    private static final String DEBUB = "DEBUG: ";
    private static final String NAME_TABLE = "usuario";
    private static final String[] COLUMS = new String[]{
            "id", "login", "senha"
    };
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS usuario";
    private static SQLiteDatabase db;
    private SQLiteHelper dbHelper;

    public UsuarioDAO(Context ctx) {
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


    public void salvar(long id, String login, String senha) {
        ContentValues values = new ContentValues();
        values.put(COLUMS[0], id);
        values.put(COLUMS[1], login);
        values.put(COLUMS[2], senha);
        db.insert(NAME_TABLE, null, values);
        Log.i(DEBUB, "Usuário salvo!!");
    }

    public Usuario getUsuario() {
        Cursor c = getCursor();
        if (c.moveToFirst()) {
            int id = c.getColumnIndex(COLUMS[0]);
            int login = c.getColumnIndex(COLUMS[1]);
            int senha = c.getColumnIndex(COLUMS[2]);
            Usuario u = new Usuario();
            u.setId(c.getLong(id));
            u.setLogin(c.getString(login));
            u.setSenha(c.getString(senha));
            return u;
        }
        return null;
    }

}