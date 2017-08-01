package com.example.ahmet.erasmusapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.ahmet.erasmusapp.SQL_Veritabanı_MesajModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yenen on 13.3.2017.
 */
public class SQL_MesajVeritabanı extends SQLiteOpenHelper {
    private static final String DATABASE_NAME   = "Mesajlar";
    // Contacts table name
    private static final String TABLE_COUNTRIES = "ÖzelMesaj";
    public SQL_MesajVeritabanı(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_COUNTRIES + "(baskaUserid INTEGER,KullaniciAdi TEXT,Mesaj TEXT,TarihZaman TEXT" + ")";
        Log.d("DBHelper", "SQL : " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRIES);
        onCreate(db);
    }
    public void insertCountry(SQL_Veritabanı_MesajModel sql_veritabanı_mesajModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("baskaUserid", sql_veritabanı_mesajModel.getBaskaUserid());
        values.put("KullaniciAdi", sql_veritabanı_mesajModel.getKullanıcıAdi());
        values.put("Mesaj",sql_veritabanı_mesajModel.getMesaj());
        values.put("TarihZaman",sql_veritabanı_mesajModel.getTarihZaman());
        db.insert(TABLE_COUNTRIES, null, values);
        db.close();
    }
    public void MesajlariSilme(int SilinecekId){

        try{
            SQLiteDatabase db = this.getReadableDatabase();
            db.delete(TABLE_COUNTRIES,"baskaUserid"+"="+SilinecekId,null);
            db.close();
            Log.e("Veritabanısilme","Silindi");
        }catch (Exception ex){
            Log.e("Veritabanısilme","Hata oluştu");
        }
    }
    public List<SQL_Veritabanı_MesajModel> getKullanıcıOZelmesajlarıçek(int baskaUserid) {
        List<SQL_Veritabanı_MesajModel> ozelmesaj = new ArrayList<SQL_Veritabanı_MesajModel>();
        SQLiteDatabase db = this.getWritableDatabase();
         String sqlQuery = "SELECT  * FROM " + TABLE_COUNTRIES +" Where baskaUserid="+baskaUserid;
         Cursor cursor = db.rawQuery(sqlQuery, null);
        while (cursor.moveToNext()) {
            SQL_Veritabanı_MesajModel model = new SQL_Veritabanı_MesajModel();
            model.setBaskaUserid(cursor.getInt(0));
            model.setKullanıcıAdi(cursor.getString(1));
            model.setMesaj(cursor.getString(2));
            model.setTarihZaman(cursor.getString(3));
            ozelmesaj.add(model);
            db.close();
        }
        return ozelmesaj;
    }
    public void cikis_VeritabanıTemizle(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_COUNTRIES,null,null);
            db.close();
        }catch (Exception ex){
            Log.e("Hata oluştu","SQLLİTE VERİTABANI TEMİZLE HATA");
        }

    }
}
