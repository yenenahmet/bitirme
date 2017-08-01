package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 25.12.2016.
 */
public class ArkadaslikStekleriREDModel {
    @SerializedName("userid")
    private String userid;
    @SerializedName("reduserid")
    private String reduserid;

    public ArkadaslikStekleriREDModel(String userid, String reduserid) {
        this.userid = userid;
        this.reduserid =reduserid;

    }
}
