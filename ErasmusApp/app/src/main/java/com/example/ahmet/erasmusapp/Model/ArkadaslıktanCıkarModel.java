package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 18.2.2017.
 */
public class ArkadaslıktanCıkarModel {
    @SerializedName("UserId")
    @Expose
    private String UserId; //
    @SerializedName("gönderilenuserid")
    @Expose
    private String gönderilenuserid; //

    public ArkadaslıktanCıkarModel(String UserId, String gönderilenuserid) {
        this.UserId = UserId;
        this.gönderilenuserid = gönderilenuserid;

    }
}
