package com.example.ahmet.erasmusapp.Service;



import com.example.ahmet.erasmusapp.Model.ArkdasListesiModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Ahmet on 9.9.2016.
 */
public interface ArkadasListesiService {
    @GET("/getUserArkadaslarlistesi/{UserId}")
    Call<ArkdasListesiModel[]> getArkdasListesiModel(@Path("UserId") int UserId);
}
