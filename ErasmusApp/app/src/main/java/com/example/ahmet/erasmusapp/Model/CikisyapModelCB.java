package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 20.3.2017.
 */
public class CikisyapModelCB {
    @SerializedName("regid")
    @Expose
    private String regid;
    public void setRegid(String regid){

        this.regid = regid;
    }
    public String getRegid(){

        return this.regid;
    }
}
