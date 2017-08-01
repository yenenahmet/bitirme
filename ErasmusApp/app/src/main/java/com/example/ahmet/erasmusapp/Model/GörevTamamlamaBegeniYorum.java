package com.example.ahmet.erasmusapp.Model;




import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Yenen on 21.10.2016.
 */
public class GörevTamamlamaBegeniYorum {

    @SerializedName("YorumSayisi")
    @Expose
    private int YorumSayisi;
    @SerializedName("BegeniSayisi")
    @Expose
    private int BegeniSayisi;
    @SerializedName("UserId")
    @Expose
    private int UserId;
    @SerializedName("ProfilResmi")
    @Expose
    private String ProfilResmi;
    @SerializedName("KullaniciAdi")
    @Expose
    private String KullaniciAdi;
    @SerializedName("AdımId")
    @Expose
    private int AdımId;
    @SerializedName("GörevId")
    @Expose
    private int GörevId;
    @SerializedName("TarihZaman")
    @Expose
    private String TarihZaman;
    @SerializedName("GörevAdi")
    @Expose
    private String GörevAdi;
    @SerializedName("GörevResmi")
    @Expose
    private String GörevResmi;
    @SerializedName("AdimAdi")
    @Expose
    private String AdimAdi;
    @SerializedName("AdimResmi")
    @Expose
    private String AdimResmi;
    @SerializedName("Begenildimi")
    @Expose
    private String Begenildimi;
    public void setYorumSayisi(int YorumSayisi){

        this.YorumSayisi = YorumSayisi;
    }
    public int getYorumSayisi(){

        return this.YorumSayisi;
    }
    public void setBegeniSayisi(int BegeniSayisi){

        this.BegeniSayisi = BegeniSayisi;
    }
    public int getBegeniSayisi(){

        return this.BegeniSayisi;
    }
    public void setUserId(int UserId){

        this.UserId = UserId;
    }
    public int getUserId(){

        return this.UserId;
    }
    public void setAdımId(int AdımId){
        this.AdımId =AdımId;
    }
    public int getAdımId(){

        return this.AdımId;
    }
    public void setGörevId(int GörevId){
        this.GörevId =GörevId;
    }
    public int getGörevId(){

        return this.GörevId;
    }
    public void setTarihZaman(String TarihZaman){
        this.TarihZaman =TarihZaman;
    }
    public String getTarihZaman (){

        return this.TarihZaman;
    }

    public void setGörevAdi(String GörevAdi){
        this.GörevAdi =GörevAdi;
    }
    public String getGörevAdi (){

        return this.GörevAdi;
    }
    public void setGörevResmi(String GörevResmi){
        this.GörevResmi =GörevResmi;
    }
    public String getGörevResmi (){

        return this.GörevResmi;
    }
    public void setAdimAdi(String AdimAdi){
        this.AdimAdi =AdimAdi;
    }
    public String getAdimAdi (){

        return this.AdimAdi;
    }
    public void setAdimResmi(String AdimResmi){
        this.AdimResmi =AdimResmi;
    }
    public String getAdimResmi (){

        return this.AdimResmi;
    }
    public void setProfilResmi(String ProfilResmi){
        this.ProfilResmi =ProfilResmi;
    }
    public String getProfilResmi (){

        return this.ProfilResmi;
    }
    public void setKullaniciAdi(String KullaniciAdi){
        this.KullaniciAdi =KullaniciAdi;
    }
    public String getKullaniciAdi (){

        return this.KullaniciAdi;
    }
    public void setBegenildimi(String Begenildimi){
        this.Begenildimi =Begenildimi;
    }
    public String getBegenildimi (){

        return this.Begenildimi;
    }
}
