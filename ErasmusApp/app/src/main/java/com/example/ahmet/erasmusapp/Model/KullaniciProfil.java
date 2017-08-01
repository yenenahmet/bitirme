package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmet on 6.9.2016.
 */
public class KullaniciProfil {
    @SerializedName("UserId")
    @Expose
    public int UserId;
    @SerializedName("KullaniciAdi")
    @Expose
    public String KullaniciAdi;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("Okul")
    @Expose
    public String Okul;
    @SerializedName("GörevDerecesi")
    @Expose
    public String GörevDerecesi;
    @SerializedName("ProfilYazısı")
    @Expose
    public String ProfilYazısı;
    @SerializedName("ProfilResmi")
    @Expose
    public String ProfilResmi;
    @SerializedName("bitengörevSayis")
    @Expose
    public int bitengörevSayis;

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

    public int getBitengörevSayis() {
        return bitengörevSayis;
    }

    public void setBitengörevSayis(int bitengörevSayis) {
        this.bitengörevSayis = bitengörevSayis;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }
}
