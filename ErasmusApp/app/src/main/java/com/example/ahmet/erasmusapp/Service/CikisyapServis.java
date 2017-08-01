package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriOnaylaModel;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriOnaylaModelCb;
import com.example.ahmet.erasmusapp.Model.CikisyapModel;
import com.example.ahmet.erasmusapp.Model.CikisyapModelCB;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 20.3.2017.
 */
public interface CikisyapServis {
    @POST("/api/CikisYap_Temizle")
    Call<CikisyapModelCB> cikisYapServis(@Body CikisyapModel cikisyapModel);

}
