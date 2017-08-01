package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.GörevAdimleriModel;
import com.example.ahmet.erasmusapp.Model.YorumlariGetirModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Yenen on 29.11.2016.
 */
public interface PostYorumlarıGetir {
    @GET("/getGörevTamamlamaPostYorumDısarıAcma/{userid}/{adimid}/{gorevid}")
    Call<YorumlariGetirModel[]> getyorumlarıGetir(@Path("userid") int userid, @Path("adimid") int adimid, @Path("gorevid") int gorevid);
}
