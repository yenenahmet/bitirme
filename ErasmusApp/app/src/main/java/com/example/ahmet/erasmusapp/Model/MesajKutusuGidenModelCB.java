package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 2.3.2017.
 */
public class MesajKutusuGidenModelCB {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("UserId")
    @Expose
    private String UserId;
    public void setName(String name){

        this.name = name;
    }
    public String getName(){

        return this.name;
    }
    public void setUserId(String UserId){

        this.UserId = UserId;
    }
    public String getUserId(){

        return this.UserId;
    }

}
