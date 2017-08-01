package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 20.3.2017.
 */
public class CikisyapModel {
    @SerializedName("userid")
    private String userid;
    public CikisyapModel(String userid) {
        this.userid = userid;
    }
}
