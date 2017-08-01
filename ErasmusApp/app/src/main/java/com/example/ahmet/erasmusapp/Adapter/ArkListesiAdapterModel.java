package com.example.ahmet.erasmusapp.Adapter;

/**
 * Created by Ahmet on 9.9.2016.
 */
public class ArkListesiAdapterModel {

    private int UserId;
    private String KullaniciAdi;
    private String ProfilResmi;
    private int ArkUserId;

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
    public int getArkUserId(){
        return ArkUserId;
    }
    public void setArkUserId(int ArkUserId){
        this.ArkUserId = ArkUserId;
    }
}
