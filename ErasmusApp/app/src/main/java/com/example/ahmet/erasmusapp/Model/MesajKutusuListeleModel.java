package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 6.3.2017.
 */
public class MesajKutusuListeleModel {
    @SerializedName("MyUserid")
    @Expose
    private int MyUserid; //
    @SerializedName("mesajgelenUserid")
    @Expose
    private int mesajgelenUserid; //
    @SerializedName("SonMesaj")
    @Expose
    private String SonMesaj;
    @SerializedName("SonMesajZamanı")
    @Expose
    private String SonMesajZamanı;
    @SerializedName("ProfilResmi")
    @Expose
    private String ProfilResmi;
    @SerializedName("KullaniciAdi")
    @Expose
    private String KullaniciAdi;
    public int getMyUserid(){
        return MyUserid;
    }
    public void setMyUserid(int MyUserid){
        this.MyUserid =MyUserid;
    }
    public int getMesajgelenUserid(){
        return mesajgelenUserid;
    }
    public void setMesajgelenUserid(int mesajgelenUserid){
        this.mesajgelenUserid =mesajgelenUserid;
    }
    public String getSonMesaj() {
        return SonMesaj;
    }
    public void setSonMesaj(String SonMesaj) {
        this.SonMesaj = SonMesaj;
    }
    public String getSonMesajZamanı(){
        return  SonMesajZamanı;
    }
    public void setSonMesajZamanı(String SonMesajZamanı){
        this.SonMesajZamanı = SonMesajZamanı;
    }
    public String getProfilResmi(){
        return ProfilResmi;
    }
    public void setProfilResmi(String ProfilResmi){
        this.ProfilResmi = ProfilResmi;
    }

    public String getKullaniciAdi(){
        return KullaniciAdi;
    }
    public void setKullaniciAdi(String KullaniciAdi){
        this.KullaniciAdi = KullaniciAdi;
    }
}
