package com.example.ahmet.erasmusapp.Adapter;

/**
 * Created by Ahmet on 27.9.2016.
 */
public class GörevAdimlariAModel {
    private int AdımId;
    private String AdimAdi;
    private String AdimResmi;
    private boolean TamamlanmaDurumu;

    public int getAdımId(){
        return AdımId;
    }
    public void setAdımId(int AdımId){
        this.AdımId =AdımId;
    }
    public String getAdimAdi() {
        return AdimAdi;
    }

    public void setAdimAdi(String AdimAdi) {
        this.AdimAdi = AdimAdi;
    }
    public String getAdimResmi(){
        return  AdimResmi;
    }
    public void setAdimResmi(String AdimResmi){
        this.AdimResmi = AdimResmi;
    }
    public boolean getTamamlanmaDurumu(){
        return TamamlanmaDurumu;
    }
    public void setTamamlanmaDurumu(boolean TamamlanmaDurumu){
        this.TamamlanmaDurumu = TamamlanmaDurumu;
    }
}
