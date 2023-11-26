package com.soft.afri_wifi.mes_classes;//package com.scakstoreman.mes_classes;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.afrisoft.ssvtaxe.serveur.DonneesFromMySQL;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//
//public class envoiSMSapi {
//
//    Context context;
//    String message;
//    String numeroTelephone;
//    DonneesFromMySQL donneesFromMySQL;
//    String titre;
//
//    public envoiSMSapi(Context context, String titre, String message, String numeroTelephone) {
//        this.message = message;
//        this.numeroTelephone = numeroTelephone;
//        this.context = context;
//        donneesFromMySQL = new DonneesFromMySQL();
//        this.titre = titre;
//    }
//
//    public String envoiSMSconfirmation(){
//
//        //si la respoonse est okk is insertion des deux mouvements
//
//        //recuperation de la derniere operation4  "http://www.easysendsms.com/sms/bulksms-api/bulksms-api?username=stevstev&password=esm26392&from=Isso&to=243975207836&text=Android&type=0"
//       //String  url = "https://www.easysendsms.com/sms/bulksms-api/bulksms-api?username=steestee2019&password=esm72750&from="+titre+"&to="+numeroTelephone+"&text="+message+"&type=0";
////        String url = new me_URL(context).envoiSMS(numeroTelephone,message);
////
////        String response="";
////       Log.e("Url",url.replace(" ","%20"));
////        try {
////            response = donneesFromMySQL.call(url.replace(" ","%20"));
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//
//        return "";
//    }
//
//
//    public void envoiSmsConfirmationNew(){
//        message = message.replace(" ","%20");
//        String url = "https://www.easysendsms.com/sms/bulksms-api/bulksms-api?username=steestee2019&password=esm72750&from="+titre+"&to="+numeroTelephone+"&text="+message+"&type=0";
//        final int hiteshurl = Log.i("Hiteshurl", "" + url);
//
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                ///Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
//                if(response.contains("result")){
//
//                }else if(response.equals("error")){
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("HiteshURLerror",""+error);
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        requestQueue.add(stringRequest);
//    }
//}
