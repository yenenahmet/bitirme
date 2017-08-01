package com.example.ahmet.erasmusapp.Adapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 29.11.2016.
 */
public class PostYorumlarıGetirAdapaterModel {

    private int YorumYapanId; //

    private String YorumYapanAdi; //

    private String YorumyapanProfilResmi;

    private String Yorum;

    private String YorumAtilanTarih;

    public int getYorumYapanId(){
        return YorumYapanId;
    }
    public void setYorumYapanId(int YorumYapanId){
        this.YorumYapanId =YorumYapanId;
    }

    public String getYorumYapanAdi() {
        return YorumYapanAdi;
    }

    public void setYorumYapanAdi(String YorumYapanAdi) {
        this.YorumYapanAdi = YorumYapanAdi;
    }

    public String getYorumyapanProfilResmi(){
        return  YorumyapanProfilResmi;
    }
    public void setYorumyapanProfilResmi(String YorumyapanProfilResmi){
        this.YorumyapanProfilResmi = YorumyapanProfilResmi;
    }

    public String getYorum(){
        return Yorum;
    }
    public void setYorum(String Yorum){
        this.Yorum = Yorum;
    }
    public String getYorumAtilanTarih(){
        return YorumAtilanTarih;
    }
    public void setYorumAtilanTarih(String YorumAtilanTarih){
        this.YorumAtilanTarih = YorumAtilanTarih;
    }
}
