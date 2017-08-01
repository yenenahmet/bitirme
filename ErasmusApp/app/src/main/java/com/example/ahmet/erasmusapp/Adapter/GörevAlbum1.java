package com.example.ahmet.erasmusapp.Adapter;

/**
 * Created by Ahmet on 20.9.2016.
 */
public class GörevAlbum1  {

        private int GörevId;
        private String GörevAdi;
        private String GörevResmi;

        public GörevAlbum1() {
        }

        public GörevAlbum1(int GörevId, String GörevAdi, String GörevResmi) {
            this.GörevId = GörevId;
            this.GörevAdi = GörevAdi;
            this.GörevResmi = GörevResmi;
        }

        public int getGörevId() {
            return GörevId;
        }

        public void setGörevId(int GörevId) {
            this.GörevId = GörevId;
        }

        public String getGörevAdi() {
            return GörevAdi;
        }

        public void setGörevAdi(String GörevAdi) {
            this.GörevAdi = GörevAdi;
        }

        public String getGörevResmi() {
            return GörevResmi;
        }

        public void setGörevResmi(String GörevResmi) {
            this.GörevResmi = GörevResmi;
        }

}
