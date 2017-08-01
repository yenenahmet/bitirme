package com.example.ahmet.erasmusapp.FoursquareApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmet on 6.10.2016.
 */
public class FourquareModel {
    @SerializedName("response")
    @Expose
    private Response response;
    public Response getResponse() {
        return response;
    }
    public void setResponse(Response response) {
        this.response = response;
    }
    public class Response {
        @SerializedName("venues")
        @Expose
        private List<Venue> venues = new ArrayList<Venue>();
        public List<Venue> getVenues() {
            return venues;
        }
        public void setVenues(List<Venue> venues) {
            this.venues = venues;
        }
    }
     class Venue {
        @SerializedName("name")
        @Expose
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
