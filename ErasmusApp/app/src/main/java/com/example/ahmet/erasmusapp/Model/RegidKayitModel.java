package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 23.12.2016.
 */
public class RegidKayitModel {
    @SerializedName("UserId")
    private String UserId;
    @SerializedName("Regid")
    private String Regid;

    public RegidKayitModel(String UserId,String Regid) {
        this.UserId = UserId;
        this.Regid = Regid;
    }
}
