package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 24.12.2016.
 */
public class ArkadaslikStekleriOnaylaModelCb {
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
