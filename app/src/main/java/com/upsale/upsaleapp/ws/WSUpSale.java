package com.upsale.upsaleapp.ws;

import com.upsale.upsaleapp.model.Categoria;
import com.upsale.upsaleapp.model.Estoque;
import com.upsale.upsaleapp.model.Venda;
import com.upsale.upsaleapp.ws.http.Get;
import com.upsale.upsaleapp.ws.http.Put;
import com.upsale.upsaleapp.ws.task.AsyncTaskAutenticar;
import com.upsale.upsaleapp.ws.task.AsyncTaskGetCategoria;
import com.upsale.upsaleapp.ws.task.AsyncTaskGetEstoque;
import com.upsale.upsaleapp.ws.task.AsyncTaskGetVenda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Mauricio R. Vidal on 19/05/2016.
 */
public class WSUpSale {

    private static final String HOST = "http://192.168.1.106:8080/UpSale/webresources/upsale/";

    public static Integer autenticar(String login, String senha){
        Map<String, Object> form = new HashMap<>();
        form.put("login", login);
        form.put("senha", senha);
        try {
            return new AsyncTaskAutenticar().execute(new Put(HOST+"autenticacao", form)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Categoria> getCategorias(){
        try {
            return new AsyncTaskGetCategoria().execute(new Get(HOST+"listar/categoria")).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Estoque> getEstoques(){
        try {
            return new AsyncTaskGetEstoque().execute(new Get(HOST+"listar/estoque")).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Venda> getVendas(){
        try {
            return new AsyncTaskGetVenda().execute(new Get(HOST+"listar/venda")).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


}