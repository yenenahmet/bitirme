package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.GörevlerEtap1Model;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Ahmet on 20.9.2016.
 */
public interface GörevlerEtap1Service {
    @GET("/getGörevlerEtap1/GörevlerDirekt")
    Call<GörevlerEtap1Model[]> getGörevlerAlbum();
}
