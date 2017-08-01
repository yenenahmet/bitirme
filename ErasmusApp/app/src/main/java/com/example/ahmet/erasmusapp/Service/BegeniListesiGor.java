package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.ArkdasListesiModel;
import com.example.ahmet.erasmusapp.Model.BegenenListesiGorModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Yenen on 9.2.2017.
 */
public interface BegeniListesiGor {
    @GET("/api/BegeniListesiDodur_FragA_Yorumlar/{adimid}/{gorevid}/{gonderiuserid}")
    Call<BegenenListesiGorModel[]> getBegeniDoldurYorumlarListesiModel(@Path("adimid") int adimid, @Path("gorevid") int gorevid, @Path("gonderiuserid") int gonderiuserid);
}
