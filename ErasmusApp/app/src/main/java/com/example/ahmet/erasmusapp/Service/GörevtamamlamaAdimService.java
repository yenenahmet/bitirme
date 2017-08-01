package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.GörevtamamlamaPostAdim;
import com.example.ahmet.erasmusapp.Model.GörevtamamlamaPostAdimCb;
import com.example.ahmet.erasmusapp.Model.Task;
import com.example.ahmet.erasmusapp.Model.Taskcb;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 24.10.2016.
 */
public interface GörevtamamlamaAdimService {
    @POST("/api/postGörevtamamlamaAdim")
    Call<GörevtamamlamaPostAdimCb> GörevtamamlamaAdimServiceF(@Body GörevtamamlamaPostAdim görevpost);


}
