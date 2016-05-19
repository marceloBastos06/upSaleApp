package com.upsale.upsaleapp.database;


public class DatabaseCreator {
    //cria tabela com ID sequencial

    //"insert into waterhistory(quantity, hour, date) values (0, '0:00', '00/00/00');"

    public static final String[] SCRIPT_DATABASE_CREATE = new String[]{
            "CREATE TABLE mobile_clientes (\n" +
                    "iddados  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "idnutri  INTEGER(11) NOT NULL,\n" +
                    "idcliente  INTEGER(11) NOT NULL,\n" +
                    "nome  TEXT(200) NOT NULL,\n" +
                    "email  TEXT(200) NOT NULL,\n" +
                    "telefone  TEXT(200) NOT NULL,\n" +
                    "chat  INTEGER NOT NULL\n" +
                    ");",
    };

    public static final String NAME_DATABASE = "upsale.db";

    public static final int VERSION = 1;
}
