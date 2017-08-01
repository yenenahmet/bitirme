package com.example.ahmet.erasmusapp;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.Model.Task;
import com.example.ahmet.erasmusapp.Model.Taskcb;
import com.example.ahmet.erasmusapp.Service.interfaceLoginKayit;
import com.example.ahmet.erasmusapp.picasso.CircleTransformation;

import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class KayitAct extends AppCompatActivity {
    public    EditText edtuser;
    public    EditText edtemail;
    public    EditText edtpassword;
    private static int RESULT_LOAD_IMAGE = 1;
    private  ImageView defaultResim;
    private Bitmap Resimyolla;
// Resim eklicez Yollıcaz , Serverdan kontro let 20 karakterten fazla olmasın
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);
        Button btnkayit = (Button)findViewById(R.id.button);
         edtuser = (EditText)findViewById(R.id.editText);

         edtemail = (EditText)findViewById(R.id.editText3);
         edtpassword = (EditText)findViewById(R.id.editText4);
        defaultResim =(ImageView)findViewById(R.id.KayitResimDefault);
        defaultResim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    Log.e("asdasdas","chek ilk if");
                } else {
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                    Log.e("CurrenCalisti","gresimperrg");
                }


            }
        });
        assert btnkayit != null;
        btnkayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtemail.length() < 6 || edtpassword.length() < 6 || edtuser.length() < 6) {
                    if (EmailValidator.isEmailValid(edtemail.getText().toString())) {
                        try {
                            kayit(Resimyolla);
                        }catch (NullPointerException ex){
                            Toast.makeText(getApplicationContext(), "Resim Seçmediniz !!!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Email adresi yanlış!!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "6 karakterden az !!!", Toast.LENGTH_LONG).show();
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
            ImageView imageView = (ImageView) findViewById(R.id.KayitResimDefault);


            CircleTransformation ab = new CircleTransformation();
            imageView.setImageBitmap(ab.transform(BitmapFactory.decodeFile(picturePath)));
            Resimyolla = BitmapFactory.decodeFile(picturePath);
        }


    }
    public void kayit(Bitmap Resim){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Resim.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        interfaceLoginKayit lgn = retrofit.create(interfaceLoginKayit.class);

        Task task =new Task(edtemail.getText().toString(), edtuser.getText().toString(), edtpassword.getText().toString(), imageString);

        Call<Taskcb> call = lgn.createTask(task);
        call.enqueue(new Callback<Taskcb>() {
                @Override
                public void onResponse(Response<Taskcb> response, Retrofit retrofit) {
                    if(response.isSuccess()){
                        Toast.makeText(getApplicationContext(), "Kayıt Tamamlandı ...", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), response.body().getName(), Toast.LENGTH_LONG).show();
                        finish();
                    }else{
                        Log.e("elsehatasıoluştu","HATAAAAAAAAAAa");
                    }

                }

                @Override
                public void onFailure(Throwable t) {
                    String merror = t.getLocalizedMessage();
                    Log.v("hello", merror);
                    Toast.makeText(getApplicationContext(), "Fail Connections !", Toast.LENGTH_LONG).show();
                }
            });
    }

    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED ){
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                } else {
                    Toast.makeText(this,"İzinleri açmadan Malesef Uygulamayı sağlıklı kullanamassınız !!!",Toast.LENGTH_LONG);
                }
                return;
            }

        }
    }



}




class EmailValidator{
    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";

    public static boolean isEmailValid(String emailInput) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN,
                Pattern.CASE_INSENSITIVE);
        return pattern.matcher(emailInput).find();
    }
}

