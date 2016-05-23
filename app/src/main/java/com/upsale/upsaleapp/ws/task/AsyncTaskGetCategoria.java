package com.upsale.upsaleapp.ws.task;

import com.google.gson.reflect.TypeToken;
import com.upsale.upsaleapp.model.Categoria;
import com.upsale.upsaleapp.ws.http.Http;

import java.io.IOException;
import java.util.List;

/**
 * Created by Mauricio R. Vidal on 19/05/2016.
 */
public class AsyncTaskGetCategoria extends Task<Object, Void, List<Categoria>>{

    @Override
    protected List<Categoria> doInBackground(Object... params) {
        try {
            Http http = (Http) params[0];
            String json = http.solicitar();
            return GSON.fromJson(json, new TypeToken<List<Categoria>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
