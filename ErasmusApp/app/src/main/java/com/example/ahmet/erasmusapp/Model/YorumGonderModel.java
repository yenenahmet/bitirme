package com.example.ahmet.erasmusapp.Model;

import android.text.Editable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 30.11.2016.
 */
public class YorumGonderModel {
    @SerializedName("userid")
    private String userid;
    @SerializedName("görevid")
    private String görevid;
    @SerializedName("adimid")
    private String adimid;
    @SerializedName("yorum")
    private String yorum;
    @SerializedName("yorumyapanid")
    private String yorumyapanid;
    @SerializedName("yorumyapanad")
    private String yorumyapanad;
    @SerializedName("yorumyapanProfilresmi")
    private String yorumyapanProfilresmi;

    public YorumGonderModel(String userid, String görevid, String adimid, String yorum, String yorumyapanid, String yorumyapanad, String yorumyapanProfilresmi) {
        this.userid = userid;
        this.yorum = yorum;
        this.yorumyapanad = yorumyapanad;
        this.yorumyapanProfilresmi = yorumyapanProfilresmi;
        this.görevid = görevid;
        this.adimid=adimid;
        this.yorumyapanid = yorumyapanid;

    }
}
