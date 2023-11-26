package com.soft.afri_wifi.serveur;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class sendDataPost {

//        implementation 'com.fasterxml.jackson.core:jackson-databind:2.7.3'
    public static HashMap<String, String> parameters(Object obj) {
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, String> map = mapper.convertValue(obj, new TypeReference<Map<String, String>>() {});

        HashMap<String, String> hashMap =
                (map instanceof HashMap)
                        ? (HashMap) map
                        : new HashMap<String, String>(map);
        return hashMap;
    }

    public static Map<String, String> objectToMap(Object obj) {
        final ObjectMapper mapper = new ObjectMapper();
        final Map<String, String> map = mapper.convertValue(obj, new TypeReference<Map<String, String>>() {});


        return map;
    }


    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder feedback = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                feedback.append("&");
            feedback.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            feedback.append("=");
            feedback.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return feedback.toString();
    }
    private static String getHasMapToJSONString(HashMap<String, String> params) {

        return  new JSONObject(params).toString();

    }

    public static String getReponse(Context myContext, HashMap<String, String> MyData, String url) throws IOException {

//        String response = "";
//        URL url_2 = new URL( url);
//        Log.e("url",url);
//        HttpURLConnection client = null;
//        try {
//            Log.e("responseGG",response);
//            client = (HttpURLConnection) url_2.openConnection();
//            client.setRequestMethod("POST");
//            client.setRequestProperty("Content-Type", "application/json; utf-8");
//            client.setRequestProperty("Accept", "application/json");
////            client.setRequestProperty("charset", "utf-8");
//            // You need to specify the context-type.  In this case it is a
//            // form submission, so use "multipart/form-data"
//            //client.setRequestProperty("multipart/form-data", "https://eddn.usgs.gov/fieldtest.html;charset=UTF-8");
//            client.setDoInput(true);
//            client.setDoOutput(true);
//
//            OutputStream os = client.getOutputStream();
//                byte[] input = getHasMapToJSONString(MyData).getBytes("utf-8");
//                os.write(input, 0, input.length);
////            }
//
//            Log.e("data",getHasMapToJSONString(MyData));
//
//            // OutputStream os = client.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"));
//
//
////            writer.write())getHasMapToJSONString(MyData;
////            writer.write("{Libele : jusind}");
//
//            writer.flush();
//            writer.close();
//            os.close();
//            int responseCode = client.getResponseCode();
//
//            if (responseCode == HttpsURLConnection.HTTP_OK) {
//                String line;
//                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
//                while ((line = br.readLine()) != null) {
//                    response += line;
//                }
//            }
//            else {
//                response = "";
//            }
//        }
//        catch (MalformedURLException e){
//            e.printStackTrace();
//
//
//        }
//        finally {
//            if(client != null) // Make sure the connection is not null.
//                client.disconnect();
//        }
//        Log.e("error",response);
//        return response;



        String response = "";
        URL url_2 = new URL( url);
        Log.e("url",url);
        HttpURLConnection client = null;
        try {
            Log.e("responseGG",response);
            client = (HttpURLConnection) url_2.openConnection();
            client.setRequestMethod("POST");
            // You need to specify the context-type.  In this case it is a
            // form submission, so use "multipart/form-data"
            //client.setRequestProperty("multipart/form-data", "https://eddn.usgs.gov/fieldtest.html;charset=UTF-8");
            client.setDoInput(true);
            client.setDoOutput(true);

            OutputStream os = client.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
//            writer.write(getHasMapToJSONString(MyData));
            writer.write(getPostDataString(MyData));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = client.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            }
            else {
                response = "";
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
            Log.e("eror",e.toString());


        }
        finally {
            if(client != null) // Make sure the connection is not null.
                client.disconnect();
        }
        Log.e("response",response+" Justin");
        return response;
    }

}
