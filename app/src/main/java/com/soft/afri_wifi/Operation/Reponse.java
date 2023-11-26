package com.soft.afri_wifi.Operation;

import com.google.gson.annotations.SerializedName;

public class Reponse {
    @SerializedName("success")
    public boolean succes;
    @SerializedName("message")
    public String message;

    public Reponse(boolean succes, String message) {
        this.succes = succes;
        this.message = message;
    }

    public boolean isSucces() {
        return succes;
    }

    public String getMessage() {
        return message;
    }
}
