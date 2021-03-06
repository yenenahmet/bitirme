package com.example.ahmet.erasmusapp.Adapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Yenen on 30.10.2016.
 */
public class BildirimleriDöndürAdapterModel {

    private int Myid; //
    private int BildirimiGöderenId; //
    private String TarihZaman;
    private boolean Yorum;
    private int GörevId;
    private int AdimId;
    private boolean bildirimeBakıldımı;
    private boolean begeni;
    private boolean ArkadasEkledin;
    private boolean ArkadaslıkİsteğiGeldi;
    private boolean MesajGeldi;
    private String KullaniciAdi;
    private String ProfilResmi;

    public int getMyid(){
        return Myid;
    }
    public void setMyid(int Myid){
        this.Myid =Myid;
    }
    public int getBildirimiGöderenId() {
        return BildirimiGöderenId;
    }

    public void setBildirimiGöderenId(int BildirimiGöderenId) {
        this.BildirimiGöderenId = BildirimiGöderenId;
    }
    public String getTarihZaman(){
        return  TarihZaman;
    }
    public void setTarihZaman(String TarihZaman){
        this.TarihZaman = TarihZaman;
    }
    public boolean getYorum(){
        return Yorum;
    }
    public void setYorum(boolean Yorum){
        this.Yorum = Yorum;
    }
    public int getGörevId(){
        return GörevId;
    }
    public void setGörevId(int GörevId){
        this.GörevId =GörevId;
    }
    public int getAdimId(){
        return AdimId;
    }
    public void setAdimId(int AdimId){
        this.AdimId =AdimId;
    }
    public boolean getBildirimeBakıldımı(){
        return bildirimeBakıldımı;
    }
    public void setBildirimeBakıldımı(boolean bildirimeBakıldımı){
        this.bildirimeBakıldımı =bildirimeBakıldımı;
    }
    public boolean getBegeni(){
        return begeni;
    }
    public void setBegeni(boolean begeni){
        this.begeni =begeni;
    }
    public boolean getArkadasEkledin(){
        return ArkadasEkledin;
    }
    public void setArkadasEkledin(boolean ArkadasEkledin){
        this.ArkadasEkledin =ArkadasEkledin;
    }
    public boolean getArkadaslıkİsteğiGeldi(){
        return ArkadaslıkİsteğiGeldi;
    }
    public void setArkadaslıkİsteğiGeldi(boolean ArkadaslıkİsteğiGeldi){
        this.ArkadaslıkİsteğiGeldi =ArkadaslıkİsteğiGeldi;
    }
    public boolean getMesajGeldi(){
        return MesajGeldi;
    }
    public void setMesajGeldi(boolean MesajGeldi){
        this.MesajGeldi =MesajGeldi;
    }
    public String getKullaniciAdi(){
        return KullaniciAdi;
    }
    public void setKullaniciAdi(String KullaniciAdi){
        this.KullaniciAdi =KullaniciAdi;
    }
    public String getProfilResmi(){
        return ProfilResmi;
    }
    public void setProfilResmi(String ProfilResmi){
        this.ProfilResmi =ProfilResmi;
    }
}
