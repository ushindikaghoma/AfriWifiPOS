package com.soft.afri_wifi.serveur;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class sendDataPostJSON {
    //    implementation 'com.android.volley:volley:1.2.1'
    public  static String sendDataPOST(Context context, String url, JSONObject jsonObject1){

        String reponses = "";

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url, jsonObject1, future, future);

        requestQueue.add(request);
        try {
            JSONObject response = future.get(); // this will block
            reponses =  response.toString();
        } catch (InterruptedException e) {
            // exception handling
            reponses =  e.toString();
        } catch (ExecutionException e) {
            // exception handling
            e.printStackTrace();
            reponses =  e.toString();
        }

        return  reponses;
    }


    public static JSONObject objectToJSOB(Class myObject) throws JSONException {
        String jsonInString = new Gson().toJson(myObject);
        JSONObject mJSONObject = new JSONObject(jsonInString);
        return  mJSONObject;
    }

}
