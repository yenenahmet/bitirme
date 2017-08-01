package com.example.ahmet.erasmusapp.Model;

/**
 * Created by Yenen on 8.3.2017.
 */
public class MesajÖzelDenemeModel {
    private int baskaUserid;
    private String KullanıcıAdi;
    private String Mesaj;
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

}
