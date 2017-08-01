package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 2.3.2017.
 */
public class MesajKutusuGidenModel {

    @SerializedName("gönderilenUserid")
    private String gönderilenUserid;
    @SerializedName("Myuserid")
    private String Myuserid;
    @SerializedName("Mesaj")
    private String Mesaj;

    public MesajKutusuGidenModel(String gönderilenUserid, String Myuserid, String Mesaj) {
        this.gönderilenUserid = gönderilenUserid;
        this.Myuserid = Myuserid;
        this.Mesaj = Mesaj;
    }
}
