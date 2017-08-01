package com.example.ahmet.erasmusapp.Service;



import com.example.ahmet.erasmusapp.Model.ArkadaslikistekleriModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Yenen on 6.11.2016.
 */
public interface ArkadaslikistekleriService {
    @GET("/getArkadaslikistekleri/{UserId}")
    Call<ArkadaslikistekleriModel[]> getArkadaslikistekleri(@Path("UserId") int UserId);
}
