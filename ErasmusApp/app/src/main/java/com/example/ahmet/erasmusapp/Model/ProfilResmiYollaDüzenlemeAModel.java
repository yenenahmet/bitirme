package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 12.2.2017.
 */
public class ProfilResmiYollaDüzenlemeAModel {
    @SerializedName("resimyolu")
    private String resimyolu;
    @SerializedName("email")
    private String email;
    @SerializedName("userid")
    private String userid;
    @SerializedName("Sifre")
    private String Sifre;
    @SerializedName("profilyazısı")
    private String profilyazısı;
    @SerializedName("KullaniciAdi")
    private String KullaniciAdi;
    public ProfilResmiYollaDüzenlemeAModel(String resimyolu, String email,String userid,String sifre,String kullaniciAdi,String profilyazısı) {
        this.resimyolu = resimyolu;
        this.email = email;
        this.userid = userid;
        this.Sifre =sifre;
        this.KullaniciAdi =kullaniciAdi;
        this.profilyazısı = profilyazısı;

    }
}
