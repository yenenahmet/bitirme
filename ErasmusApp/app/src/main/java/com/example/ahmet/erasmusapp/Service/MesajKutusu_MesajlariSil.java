package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.MesajKutusu_MesajlarıSilModel;
import com.example.ahmet.erasmusapp.Model.MesajKutusu_MesajlarıSilModelCB;
import com.example.ahmet.erasmusapp.Model.RegidKayitModel;
import com.example.ahmet.erasmusapp.Model.RegidKayitModelCB;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 16.3.2017.
 */
public interface MesajKutusu_MesajlariSil {
    @POST("/api/MesajKutusuMesajlarıSil")
    Call<MesajKutusu_MesajlarıSilModelCB> mesajsil(@Body MesajKutusu_MesajlarıSilModel mesajKutusu_mesajlarıSilModel);
}
