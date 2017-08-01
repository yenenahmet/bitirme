package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 2.2.2017.
 */
public class BegeniGeriAlModel {
    @SerializedName("AdimId")
    private String AdimId;
    @SerializedName("GörevId")
    private String GörevId;
    @SerializedName("MyUserId")
    private String MyUserId;
    @SerializedName("BildirimGönderenId")
    private String BildirimGönderenId;

    public BegeniGeriAlModel(String adimId, String görevId,String myUserId,String bildirimGönderenId) {
        this.AdimId = adimId;
        this.GörevId = görevId;
        this.MyUserId = myUserId;
        this.BildirimGönderenId =bildirimGönderenId;

    }
}
