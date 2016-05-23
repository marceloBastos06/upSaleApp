package com.upsale.upsaleapp.ws.task;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Mauricio R. Vidal on 23/05/2016.
 */
public abstract class Task<Param,Progress, Result> extends AsyncTask<Param, Progress, Result>{
    protected static final Gson GSON = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
}
