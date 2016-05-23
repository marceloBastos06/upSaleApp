package com.upsale.upsaleapp.ws.task;

import com.google.gson.reflect.TypeToken;
import com.upsale.upsaleapp.model.Produto;
import com.upsale.upsaleapp.ws.http.Http;

import java.io.IOException;
import java.util.List;

/**
 * Created by Mauricio R. Vidal on 23/05/2016.
 */
public class AsyncTaskGetProduto extends Task<Object, Void, List<Produto>> {
    @Override
    protected List<Produto> doInBackground(Object... params) {
        try {
            Http http = (Http) params[0];
            String json = http.solicitar();
            return GSON.fromJson(json, new TypeToken<List<Produto>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
