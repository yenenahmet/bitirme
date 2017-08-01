package com.example.ahmet.erasmusapp.FoursquareApi;


import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Ahmet on 6.10.2016.
 */

public interface FService {
    @GET("/v2/venues/search")
    Call<FourquareModel> getFourquaremodelQuery(@Query("client_id") String client_id,@Query("client_secret") String client_secret,@Query("v") String v,@Query("ll") String ll,@Query("radius") String radius,@Query("categoryId") String categoryId);

}
