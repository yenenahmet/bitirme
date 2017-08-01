package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 18.2.2017.
 */
public class ArkadaslıktanCıkarModelCB {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("UserId")
    @Expose
    private int UserId;
    public void setName(String name){

        this.name = name;
    }
    public String getName(){

        return this.name;
    }
    public void setUserId(int UserId){

        this.UserId = UserId;
    }
    public int getUserId(){

        return this.UserId;
    }
}
