package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 6.11.2016.
 */
public class ArkadaslikistekleriModel {
    @SerializedName("UserId")
    @Expose
    private int UserId; //
    @SerializedName("KullaniciAdi")
    @Expose
    private String KullaniciAdi; //
    @SerializedName("ProfilResmi")
    @Expose
    private String ProfilResmi;

    public int getUserId(){
        return UserId;
    }
    public void setUserId(int UserId){
        this.UserId =UserId;
    }
    public String getKullaniciAdi() {
        return KullaniciAdi;
    }

    public void setKullaniciAdi(String KullaniciAdi) {
        this.KullaniciAdi = KullaniciAdi;
    }
    public String getProfilResmi(){
        return  ProfilResmi;
    }
    public void setProfilResmi(String ProfilResmi){
        this.ProfilResmi = ProfilResmi;
    }

}
