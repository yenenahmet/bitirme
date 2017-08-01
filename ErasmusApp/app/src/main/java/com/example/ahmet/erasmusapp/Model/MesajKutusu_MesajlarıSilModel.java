package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 16.3.2017.
 */
public class MesajKutusu_MesajlarıSilModel {
    @SerializedName("UserId")
    private String UserId;
    @SerializedName("mesajgelenUserid")
    private String mesajgelenUserid;

    public MesajKutusu_MesajlarıSilModel(String UserId,String mesajgelenUserid) {
        this.UserId = UserId;
        this.mesajgelenUserid = mesajgelenUserid;
    }
}
