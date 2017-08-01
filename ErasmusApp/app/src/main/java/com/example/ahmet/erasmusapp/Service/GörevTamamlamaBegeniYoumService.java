package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.ArkdasListesiModel;
import com.example.ahmet.erasmusapp.Model.GörevTamamlamaBegeniYorum;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Yenen on 21.10.2016.
 */
public interface GörevTamamlamaBegeniYoumService {
    @GET("/getGörevtamamlamabegeniyorum/{UserId}")
    Call<GörevTamamlamaBegeniYorum[]> getGörevTamamlamaBegeniYoumService(@Path("UserId") int UserId);
}
