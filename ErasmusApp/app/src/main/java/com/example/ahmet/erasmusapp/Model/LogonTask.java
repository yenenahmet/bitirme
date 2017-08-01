package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmet on 31.8.2016.
 */
public class LogonTask {
    @SerializedName("email")
    private String email;
    @SerializedName("Sifre")
    private String Sifre;

    public LogonTask(String email,String Sifre) {
        this.email = email;
        this.Sifre = Sifre;
    }
}

