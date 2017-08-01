package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.LogonTask;
import com.example.ahmet.erasmusapp.Model.LogonTaskcb;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Ahmet on 8.9.2016.
 */
public interface LogonService {
    @POST("/api/Satinalma/login")
    Call<LogonTaskcb> createTask(@Body LogonTask logonTask);
}
