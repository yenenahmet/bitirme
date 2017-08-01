package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmet on 20.9.2016.
 */
public class GörevlerEtap1Model {

    @SerializedName("GörevId")
    @Expose
    public int GörevId; //
    @SerializedName("GörevAdi")
    @Expose
    public String GörevAdi; //
    @SerializedName("GörevResmi")
    @Expose
    public String GörevResmi;

    public int getGörevId(){
        return GörevId;
    }
    public void setGörevId(int GörevId){
        this.GörevId =GörevId;
    }
    public String getGörevAdi() {
        return GörevAdi;
    }

    public void setGörevAdi(String GörevAdi) {
        this.GörevAdi = GörevAdi;
    }
    public String getGörevResmi() {
        return GörevResmi;
    }

    public void setGörevResmi(String GörevResmi) {
        this.GörevResmi = GörevResmi;
    }
}
