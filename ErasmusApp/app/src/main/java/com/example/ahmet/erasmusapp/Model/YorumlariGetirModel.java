package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Yenen on 29.11.2016.
 */
public class YorumlariGetirModel {

    @SerializedName("YorumYapanId")
    @Expose
    private int YorumYapanId; //

    @SerializedName("YorumYapanAdi")
    @Expose
    private String YorumYapanAdi; //

    @SerializedName("YorumyapanProfilResmi")
    @Expose
    private String YorumyapanProfilResmi;

    @SerializedName("Yorum")
    @Expose
    private String Yorum;

    @SerializedName("YorumAtilanTarih")
    @Expose
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
    public void setYorumyapanProfilResmi(String ProfilResmi){
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
