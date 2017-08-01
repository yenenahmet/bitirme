package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmet on 26.9.2016.
 */
public class GörevAdimleriModel {
    @SerializedName("AdımId")
    @Expose
    public int AdımId; //
    @SerializedName("AdimAdi")
    @Expose
    public String AdimAdi; //
    @SerializedName("AdimResmi")
    @Expose
    public String AdimResmi;
    @SerializedName("TamamlanmaDurumu")
    @Expose
    public boolean TamamlanmaDurumu;

    public int getAdımId(){
        return AdımId;
    }
    public void setAdımId(int AdımId){
        this.AdımId =AdımId;
    }
    public String getAdimAdi() {
        return AdimAdi;
    }

    public void setAdimAdi(String GörevAdi) {
        this.AdimAdi = GörevAdi;
    }
    public String getAdimResmi() {
        return AdimResmi;
    }

    public void setAdimResmi(String GörevResmi) {
        this.AdimResmi = GörevResmi;
    }
    public boolean getTamamlanmaDurumu() {
        return TamamlanmaDurumu;
    }

    public void setTamamlanmaDurumu(boolean GörevResmi) {
        this.TamamlanmaDurumu = GörevResmi;
    }
}
