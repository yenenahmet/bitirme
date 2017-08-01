package com.example.ahmet.erasmusapp.Adapter;

/**
 * Created by Ahmet on 17.9.2016.
 */
public class GörevlerAlbum {
    private String name;
    private int numOfSongs;
    private int thumbnail;

    public GörevlerAlbum() {
    }

    public GörevlerAlbum(String name, int numOfSongs, int thumbnail) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
