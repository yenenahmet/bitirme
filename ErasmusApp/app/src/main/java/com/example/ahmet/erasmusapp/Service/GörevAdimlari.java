package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.GörevAdimleriModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Ahmet on 27.9.2016.
 */
public interface GörevAdimlari {

    @GET("/getGörevAdımlariUser/{userid}/{gorevid}")
    Call<GörevAdimleriModel[]> getGörevAdimlariModel(@Path("userid") int userid,@Path("gorevid") int gorevid);

}
