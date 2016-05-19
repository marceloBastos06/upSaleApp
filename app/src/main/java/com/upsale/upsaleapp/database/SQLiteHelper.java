package com.upsale.upsaleapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mauricio on 11/12/2015.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = "BANCO_DE_DADOS_HELPER";
    private String[] scriptSQLCreate;
    private String scriptSQLDelete;

    //singleton
    private static SQLiteHelper sInstance;

    public static SQLiteHelper getInstance(Context context, String name,  int version, String[] scriptSQLCreate, String scriptSQLDelete) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new SQLiteHelper(context, name, version, scriptSQLCreate, scriptSQLDelete);
        }
        return sInstance;
    }


    public static void closeConnection(){
        SQLiteDatabase db = sInstance.getWritableDatabase();
        int soma = 0;
        soma += db.delete("perfil", null, null);
        soma += db.delete("mobile_chat", null, null);
        soma += db.delete("mobile_clientes", null, null);
        soma += db.delete("mobile_consultas", null, null);
        soma += db.delete("mobile_planos", null, null);
        soma += db.delete("mobile_prescricao", null, null);
        soma += db.delete("mobile_sinc", null, null);
        System.out.println("DEBUG  numero de deletacao: "+ soma);
        sInstance.close();
        sInstance = null;
    }


    private SQLiteHelper(Context context, String name, int version, String[] scriptSQLCreate, String scriptSQLDelete) {
        super(context, name, null, version);
        this.scriptSQLCreate = scriptSQLCreate;
        this.scriptSQLDelete = scriptSQLDelete;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Criando banco de dados SQL");
        int numberOfScripts = scriptSQLCreate.length;
        for (int i=0; i<numberOfScripts; i++){
            String sql = scriptSQLCreate[i];
            Log.i(TAG, "SQL: " + sql);
            //crate database
            db.execSQL(sql);
        }
    }

    //se mudou a versao
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Atualizando a versão " + oldVersion + " para " + newVersion + ". Registros serão deletados");
        Log.i(TAG, scriptSQLDelete);
        //deleta as tabelas
        db.execSQL(scriptSQLDelete);
        //cria denovo
        onCreate(db);
    }
}