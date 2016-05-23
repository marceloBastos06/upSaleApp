package com.upsale.upsaleapp.ws.task;

import com.google.gson.reflect.TypeToken;
import com.upsale.upsaleapp.model.ItemVenda;
import com.upsale.upsaleapp.ws.http.Http;

import java.io.IOException;
import java.util.List;

/**
 * Created by Mauricio R. Vidal on 23/05/2016.
 */
public class AsyncTaskGetItemVenda extends  Task<Object, Void, List<ItemVenda>>{
    @Override
    protected List<ItemVenda> doInBackground(Object... params) {
        try {
            Http http = (Http) params[0];
            String json = http.solicitar();
            return GSON.fromJson(json, new TypeToken<List<ItemVenda>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
