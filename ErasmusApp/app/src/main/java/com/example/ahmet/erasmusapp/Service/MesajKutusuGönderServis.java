package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.LogonTask;
import com.example.ahmet.erasmusapp.Model.LogonTaskcb;
import com.example.ahmet.erasmusapp.Model.MesajKutusuGidenModel;
import com.example.ahmet.erasmusapp.Model.MesajKutusuGidenModelCB;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 2.3.2017.
 */
public interface MesajKutusuGönderServis {
    @POST("/api/MesajKutusu/Gonder")
    Call<MesajKutusuGidenModelCB> mesajgonderservis(@Body MesajKutusuGidenModel mesajKutusuGidenModel);
}
