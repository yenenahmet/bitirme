package com.example.ahmet.erasmusapp.Service;


import com.example.ahmet.erasmusapp.Model.GöreviBitirenlerModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Yenen on 1.11.2016.
 */
public interface GöreviBitirenlerService {
    @GET("/getGöreviBitirenleriGör/{gorevid}")
    Call<GöreviBitirenlerModel[]> getGöreviBitirenler(@Path("gorevid") int gorevid);
}
