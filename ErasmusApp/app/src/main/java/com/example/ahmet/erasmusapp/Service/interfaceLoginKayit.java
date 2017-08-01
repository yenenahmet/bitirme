package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.Task;
import com.example.ahmet.erasmusapp.Model.Taskcb;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Ahmet on 8.9.2016.
 */
public interface interfaceLoginKayit {
    @POST("api/kayitislemleri/kayit")
    Call<Taskcb> createTask(@Body Task task);


}
