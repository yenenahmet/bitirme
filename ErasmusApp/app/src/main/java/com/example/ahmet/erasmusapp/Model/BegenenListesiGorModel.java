package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 9.2.2017.
 */
public class BegenenListesiGorModel {
    @SerializedName("UserId")
    @Expose
    public int UserId; //
    @SerializedName("KullaniciAdi")
    @Expose
    public String KullaniciAdi; //
    @SerializedName("ProfilResmi")
    @Expose
    public String ProfilResmi;

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
