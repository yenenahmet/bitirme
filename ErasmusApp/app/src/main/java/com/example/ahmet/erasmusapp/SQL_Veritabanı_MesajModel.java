package com.example.ahmet.erasmusapp;

import java.io.Serializable;

/**
 * Created by Yenen on 13.3.2017.
 */
public class SQL_Veritabanı_MesajModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int baskaUserid;
    private String KullanıcıAdi;
    private String Mesaj;
    private String TarihZaman;
    public SQL_Veritabanı_MesajModel() {
        super();
    }
    public SQL_Veritabanı_MesajModel(int baskaUserid,String KullanıcıAdi, String Mesaj) {
        super();
        this.baskaUserid = baskaUserid;
        this.KullanıcıAdi = KullanıcıAdi;
        this.Mesaj = Mesaj;
    }
    public int getBaskaUserid(){
        return baskaUserid;
    }
    public void setBaskaUserid(int baskaUserid){
        this.baskaUserid =baskaUserid;
    }
    public String getKullanıcıAdi(){
        return KullanıcıAdi;
    }
    public void setKullanıcıAdi(String KullanıcıAdi){
        this.KullanıcıAdi =KullanıcıAdi;
    }
    public String getMesaj(){
        return  Mesaj;
    }
    public void setMesaj(String Mesaj){
        this.Mesaj = Mesaj;
    }
    public String getTarihZaman(){
        return  TarihZaman;
    }
    public void setTarihZaman(String TarihZaman){
        this.TarihZaman = TarihZaman;
    }

}
