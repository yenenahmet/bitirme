package com.example.ahmet.erasmusapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ahmet.erasmusapp.ArkadaslikistekleriGoster;
import com.example.ahmet.erasmusapp.BaskaProfileGec;
import com.example.ahmet.erasmusapp.GorevAdimlari;
import com.example.ahmet.erasmusapp.LoginAct;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriOnaylaModel;
import com.example.ahmet.erasmusapp.Model.ArkadaslikStekleriOnaylaModelCb;
import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.Service.ArkadaslikStekleriOnaylaServis;
import com.example.ahmet.erasmusapp.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;



/**
 * Created by Yenen on 6.11.2016.
 */
public class ArkadaslikistekleriAdapter extends ArrayAdapter<ArkadaslikistekleriAdapterModel> {
    private Context context;
    private ViewHolder viewHolder;
    private List<ArkadaslikistekleriAdapterModel> arrayList = new ArrayList<ArkadaslikistekleriAdapterModel>();
    public    ListView listView;
    public ArkadaslikistekleriAdapter(Context context,  List<ArkadaslikistekleriAdapterModel> list_item,ListView listView) {

        super(context, R.layout.arkadaslikistekleriitem,list_item);
        this.listView = listView;
        this.context = context;
        this.arrayList = list_item;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.arkadaslikistekleriitem, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txt_name = (TextView)view.findViewById(R.id.Kullanici_adiArkadaslikistekleri);
            viewHolder.Ark_profilresmi = (ImageView)view.findViewById(R.id.img_profil_Arkadaslikistekleri);
            viewHolder.btnKabul =(ImageView)view.findViewById(R.id.KABUL);
            viewHolder.btnRed =(ImageView)view.findViewById(R.id.red);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }

          viewHolder.txt_name.setText(arrayList.get(position).getKullaniciAdi());

        Picasso.with(getContext()).load(MyApi.URL_IMAGES+arrayList.get(position).getProfilResmi()).resize(115,115).transform(new CircleTransformation()).into(viewHolder.Ark_profilresmi);

viewHolder.btnKabul.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ArkadaslikistekleriGoster nn = new ArkadaslikistekleriGoster();

        nn.onayla(arrayList.get(position).getUserId(),context,listView);
    }
});
        viewHolder.btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArkadaslikistekleriGoster gg= new ArkadaslikistekleriGoster();
                gg.red(arrayList.get(position).getUserId(),context,listView);

            }
        });
        return  view;
    }
    public static class  ViewHolder{
        TextView txt_name;
        ImageView Ark_profilresmi;
        ImageView btnKabul;
        ImageView btnRed;
        //ListView lw;
    }

}
