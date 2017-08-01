package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.ArkdasListesiModel;
import com.example.ahmet.erasmusapp.Model.BaskasınınProfileniGecModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Yenen on 23.11.2016.
 */
public interface BaskaProfileGecServis {
    @GET("/getBaskasınınProfilineGeç/{userid}/{istekGonderilenID}")
    Call<BaskasınınProfileniGecModel[]> getBaskaProfil(@Path("userid") int UserId,@Path("istekGonderilenID") int baskaid);
}
