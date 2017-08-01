package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 30.11.2016.
 */
public class YorumGonderModelCb {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("Sonuc")
    @Expose
    private String Sonuc;
    public void setName(String name){

        this.name = name;
    }
    public String getName(){

        return this.name;
    }
    public void setSonuc(String Sonuc){

        this.Sonuc = Sonuc;
    }
    public String getSonuc(){

        return this.Sonuc;
    }
}
