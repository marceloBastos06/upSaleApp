package com.upsale.upsaleapp.ws.task;

import com.google.gson.reflect.TypeToken;
import com.upsale.upsaleapp.ws.http.Http;

import java.io.IOException;

/**
 * Created by Mauricio R. Vidal on 19/05/2016.
 */
public class AsyncTaskAutenticar extends Task<Object, Void, Integer>{

    @Override
    protected Integer doInBackground(Object... params) {
        try{
            Http http = (Http) params[0];
            String json = http.solicitar();
            return GSON.fromJson(json, new TypeToken<Integer>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
