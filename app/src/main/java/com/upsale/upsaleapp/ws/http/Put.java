package com.upsale.upsaleapp.ws.http;

import com.upsale.upsaleapp.ws.http.Http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import java.net.HttpURLConnection;

/**
 * Created by Mauricio R. Vidal on 19/05/2016.
 */
public class Put implements Http {
    private String url;
    private Map<String, Object> form;
    public Put(String url, Map<String, Object> form){
        this.url = url;
        this.form = form;
    }

    @Override
    public String solicitar() throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("PUT");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        String urlParameters = "";
        for(String chave : form.keySet()){
            String param = chave +"="+form.get(chave).toString();
            urlParameters += param + "&";
        }
        urlParameters = urlParameters.substring(0, urlParameters.length() -1);
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
