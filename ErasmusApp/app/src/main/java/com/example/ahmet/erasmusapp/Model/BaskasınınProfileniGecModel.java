package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 23.11.2016.
 */
public class BaskasınınProfileniGecModel {
    @SerializedName("MyUserid")
    @Expose
    private int MyUserid;
    @SerializedName("istekGönderilenid")
    @Expose
    private int istekGönderilenid;
    @SerializedName("KullaniciAdi")
    @Expose
    private String KullaniciAdi;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("Okul")
    @Expose
    private String Okul;
    @SerializedName("GörevDerecesi")
    @Expose
    private String GörevDerecesi;
    @SerializedName("ProfilYazısı")
    @Expose
    private String ProfilYazısı;
    @SerializedName("ProfilResmi")
    @Expose
    private String ProfilResmi;
    @SerializedName("bitengörevSayis")
    @Expose
    private int bitengörevSayis;

    public String getKullaniciAdi() {
        return KullaniciAdi;
    }
    public void setKullaniciAdi(String KullaniciAdi) {
        this.KullaniciAdi = KullaniciAdi;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getOkul() {
        return Okul;
    }
    public void setOkul(String Okul) {
        this.Okul = Okul;
    }
    public String getGörevDerecesi() {
        return GörevDerecesi;
    }
    public void setGörevDerecesi(String GörevDerecesi) {
        this.GörevDerecesi = GörevDerecesi;
    }
    public String getProfilYazısı() {
        return ProfilYazısı;
    }
    public void setProfilYazısı(String ProfilYazısı) {
        this.ProfilYazısı = ProfilYazısı;
    }
    public String getProfilResmi() {
        return ProfilResmi;
    }
    public void setProfilResmi(String ProfilResmi) {
        this.ProfilResmi = ProfilResmi;
    }
    public int getMyUserid() {
        return MyUserid;
    }
    public void setMyUserid(int MyUserid) {
        this.MyUserid = MyUserid;
    }
    public int getIstekGönderilenid() {
        return istekGönderilenid;
    }
    public void setIstekGönderilenid(int istekGönderilenid) { this.istekGönderilenid = istekGönderilenid;    }

    public int getBitengörevSayis() {
        return bitengörevSayis;
    }
    public void setBitengörevSayis(int bitengörevSayis) { this.bitengörevSayis = bitengörevSayis;    }

}
