package com.example.ahmet.erasmusapp.Adapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 6.11.2016.
 */
public class ArkadaslikistekleriAdapterModel {
    private int UserId; //
    private String KullaniciAdi; //
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
