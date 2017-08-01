package com.example.ahmet.erasmusapp.Service;

import com.example.ahmet.erasmusapp.Model.ProfilResmiYollaDüzenlemeAModel;
import com.example.ahmet.erasmusapp.Model.ProfilResmiYollaDüzenlemeAModelCB;
import com.example.ahmet.erasmusapp.Model.Task;
import com.example.ahmet.erasmusapp.Model.Taskcb;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Yenen on 12.2.2017.
 */
public interface KullaniciProfilGüncelleServis {
    @POST("/api/profilGüncelle")
    Call<ProfilResmiYollaDüzenlemeAModelCB> createTask(@Body ProfilResmiYollaDüzenlemeAModel profilResmiYollaDüzenlemeAModel);
}
