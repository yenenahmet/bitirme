package com.example.ahmet.erasmusapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.Model.ProfilResmiYollaDüzenlemeAModel;
import com.example.ahmet.erasmusapp.Model.ProfilResmiYollaDüzenlemeAModelCB;
import com.example.ahmet.erasmusapp.Service.KullaniciProfilGüncelleServis;
import com.example.ahmet.erasmusapp.TabFragmntlar.FragmentD;
import com.example.ahmet.erasmusapp.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class KisiselProfilDuzenleme extends AppCompatActivity {

    private EditText Kullanıcıadi;
    private EditText Email;
    private EditText Sifre;
    private  EditText ProfilYazisi;
    private  ImageView close;
    private  ImageView  Güncelle;
    private Bitmap Resimyolla;
    private static int RESULT_LOAD_IMAGE = 1;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisisel_profil_duzenleme);
        close =(ImageView)findViewById(R.id.cikis);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Kullanıcıadi = (EditText)findViewById(R.id.kullanıcıadiyolla);
        Kullanıcıadi.setText(FragmentD.Ad);
        Email = (EditText)findViewById(R.id.kullanıcıemailyolla);
        Email.setText(FragmentD.email);
        Sifre =(EditText)findViewById(R.id.kullanıcısifreyolla);
        ProfilYazisi =(EditText)findViewById(R.id.kullanıcıprofilyazısıyolla);
        ProfilYazisi.setText(FragmentD.profilyazısı);
        ImageView imageView = (ImageView) findViewById(R.id.profilfotosu);
        Picasso.with(getApplicationContext()).load(MyApi.URL_IMAGES+FragmentD.profilresmi1).transform(new CircleTransformation()).into(imageView);
        assert imageView != null;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });
        Güncelle= (ImageView)findViewById(R.id.onayıyolla);
        Güncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Email.length() < 6 || Sifre.length() < 6 || Kullanıcıadi.length() < 6) {
                    if (EmailValidatord.isEmailValid(Email.getText().toString())) {
                        ResmiWebServiseYolla(Resimyolla);
                    } else {
                        Toast.makeText(getApplicationContext(), "E-mail yanlış girilmiştir... ", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "6 Karakterden az giriş yapmayın lütfen !!! ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.profilfotosu);
            CircleTransformation ab = new CircleTransformation();
            Log.e("path",picturePath.toString());

            try{
                Bitmap bit = BitmapFactory.decodeFile(picturePath);
            }catch (NullPointerException ex){
                Log.e("path",ex.toString());
            }

            imageView.setImageBitmap(ab.transform(BitmapFactory.decodeFile(picturePath)));
            Resimyolla = BitmapFactory.decodeFile(picturePath);
        }


    }
    private  void ResmiWebServiseYolla(Bitmap Resim){
        progressDialog = new ProgressDialog(KisiselProfilDuzenleme.this);
        progressDialog.setMessage("Sayfa Yükleniyor Lütfen Biraz Bekleyin ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Resim.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
Log.e("resim","geldi");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        KullaniciProfilGüncelleServis servis = retrofit.create(KullaniciProfilGüncelleServis.class);
        ProfilResmiYollaDüzenlemeAModel model = new ProfilResmiYollaDüzenlemeAModel(imageString,Email.getText().toString(),String.valueOf(LoginAct.userid).toString(),Sifre.getText().toString(),Kullanıcıadi.getText().toString(),ProfilYazisi.getText().toString());
        Call<ProfilResmiYollaDüzenlemeAModelCB> call = servis.createTask(model);
        call.enqueue(new Callback<ProfilResmiYollaDüzenlemeAModelCB>() {
            @Override
            public void onResponse(Response<ProfilResmiYollaDüzenlemeAModelCB> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    Log.e("Başarılı","VVVVVV");
                    Log.e("Servistengelen : ",response.body().getName()+"--"+response.body().getUserId());
                    if(response.body().getUserId() == 1){
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(),"Güncelleme tamamlandı", Toast.LENGTH_LONG).show();
                    }else{
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(),"İnternet Baglantınızdan Kaynaklı bir hata oluştu veya sunucu yanıt vermiyor...", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Bom","VVVVVV");
                progressDialog.cancel();
                Toast.makeText(getApplicationContext(),"Güncelleme Tamamlanamadı !!! UYARI", Toast.LENGTH_LONG).show();
            }
        });

    }


}
class EmailValidatord{
    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";

    public static boolean isEmailValid(String emailInput) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN,
                Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emailInput).find();
    }
}