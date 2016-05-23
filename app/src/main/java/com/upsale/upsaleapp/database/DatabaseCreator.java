package com.upsale.upsaleapp.database;


public class DatabaseCreator {

    public static final String[] SCRIPT_DATABASE_CREATE = new String[]{
            "CREATE TABLE usuario (\n" +
                    "id  INTEGER PRIMARY KEY,\n" +
                    "nome  TEXT(200),\n" +
                    "login  TEXT(200) NOT NULL,\n" +
                    "senha  TEXT(200) NOT NULL\n" +
                    ");",
            "CREATE TABLE categoria (\n" +
                    "id  INTEGER PRIMARY KEY,\n" +
                    "nome  TEXT(200) NOT NULL\n" +
                    ");",
            "CREATE TABLE estoque (\n" +
                    "id  INTEGER PRIMARY KEY,\n" +
                    "data  TEXT(200)\n" +
                    ");",
            "CREATE TABLE item_estoque (\n" +
                    "id_estoque  INTEGER,\n" +
                    "id_produto INTEGER, \n"+
                    "quantidade  INTEGER,\n" +
                    "Primary key(id_estoque, id_produto)" +
                    ");",
            "CREATE TABLE item_venda (\n" +
                    "id_venda  INTEGER,\n" +
                    "id_produto INTEGER, \n"+
                    "quantidade  INTEGER,\n" +
                    "Primary key(id_venda, id_produto)" +
                    ");",
            "CREATE TABLE venda (\n" +
                    "id  INTEGER PRIMARY KEY,\n" +
                    "data  TEXT(200)\n" +
                    ");",
            "CREATE TABLE produto (\n" +
                    "id  INTEGER PRIMARY KEY,\n" +
                    "id_usuario INTEGER NOT NULL,\n" +
                    "id_categoria INTEGER NOT NULL,\n" +
                    "nome TEXT(200),\n" +
                    "descricao TEXT(200),\n" +
                    "preco REAL,\n"+
                    "data  TEXT(200)\n" +
                    ");",
    };

    public static final String NAME_DATABASE = "upsale.db";

    public static final int VERSION = 1;
}
