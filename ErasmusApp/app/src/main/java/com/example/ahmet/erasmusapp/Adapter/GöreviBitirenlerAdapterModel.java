package com.example.ahmet.erasmusapp.Adapter;

/**
 * Created by Yenen on 2.11.2016.
 */
public class GöreviBitirenlerAdapterModel {

    private int UserId; //
    private String KullaniciAdi; //
    private String ProfilResmi;
    private String GörevDerecesi;

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
    public String getGörevDerecesi(){
        return GörevDerecesi;
    }
    public void setGörevDerecesi(String GörevDerecesi){
        this.GörevDerecesi = GörevDerecesi;
    }
}
