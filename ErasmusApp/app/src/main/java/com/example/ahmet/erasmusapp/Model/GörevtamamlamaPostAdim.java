package com.example.ahmet.erasmusapp.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Yenen on 24.10.2016.
 */
public class GörevtamamlamaPostAdim {
    @SerializedName("UserId")
    private int UserId;
    @SerializedName("GörevId")
    private int GörevId;
    @SerializedName("TamamlanmaDurumu")
    private boolean TamamlanmaDurumu;
    @SerializedName("AdımId")
    private int AdımId;
    public GörevtamamlamaPostAdim(int UserId, int GörevId,boolean TamamlanmaDurumu,int AdımId) {
        this.UserId = UserId;
        this.GörevId = GörevId;
        this.TamamlanmaDurumu = TamamlanmaDurumu;
        this.AdımId = AdımId;
    }
}
