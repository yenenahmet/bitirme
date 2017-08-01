package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 24.10.2016.
 */
public class GörevtamamlamaPostAdimCb {
    @SerializedName("Durum")
    @Expose
    private String name;

    public void setName(String name){

        this.name = name;
    }
    public String getName(){

        return this.name;
    }
}
