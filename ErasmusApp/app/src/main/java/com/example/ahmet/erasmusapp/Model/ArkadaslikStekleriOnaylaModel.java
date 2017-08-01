package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 24.12.2016.
 */
public class ArkadaslikStekleriOnaylaModel {
    @SerializedName("UserId")
    private String UserId;
    @SerializedName("Arkuserid")
    private String Arkuserid;

    public ArkadaslikStekleriOnaylaModel(String UserId, String Arkuserid) {
        this.UserId = UserId;
        this.Arkuserid = Arkuserid;

    }
}
