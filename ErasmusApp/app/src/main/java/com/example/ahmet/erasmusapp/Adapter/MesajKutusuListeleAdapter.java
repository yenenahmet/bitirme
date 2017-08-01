package com.example.ahmet.erasmusapp.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yenen on 6.3.2017.
 */
public class MesajKutusuListeleAdapter extends ArrayAdapter<MesajKutusuListeleAdapterModel> {
    private Context context;
    private ViewHolder viewHolder;
    private List<MesajKutusuListeleAdapterModel> arrayList = new ArrayList<MesajKutusuListeleAdapterModel>();

    public MesajKutusuListeleAdapter(Context context,  List<MesajKutusuListeleAdapterModel> list_item) {

        super(context, R.layout.mesajkutusulistele_item,list_item);
        this.context = context;
        this.arrayList = list_item;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.mesajkutusulistele_item, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.Kullanici_adi_MesajKutusu = (TextView)view.findViewById(R.id.Kullanici_adi_MesajKutusu);
            viewHolder.img_profil_MesajKutusu = (ImageView)view.findViewById(R.id.img_profil_MesajKutusu);
            viewHolder.MesajKutusu_SonMesaj = (TextView)view.findViewById(R.id.MesajKutusu_SonMesaj);
            viewHolder.txt_zaman_MesajKutusun = (TextView)view.findViewById(R.id.txt_zaman_MesajKutusun);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.Kullanici_adi_MesajKutusu.setText(arrayList.get(position).getKullaniciAdi());
        viewHolder.MesajKutusu_SonMesaj.setText(arrayList.get(position).getSonMesaj());
        viewHolder.txt_zaman_MesajKutusun.setText(arrayList.get(position).getSonMesajZamanı());
        Picasso.with(getContext()).load(MyApi.URL_IMAGES+arrayList.get(position).getProfilResmi()).transform(new CircleTransformation()).into(viewHolder.img_profil_MesajKutusu);

        return  view;
    }
    public static class  ViewHolder{
        TextView Kullanici_adi_MesajKutusu;
        TextView MesajKutusu_SonMesaj;
        ImageView img_profil_MesajKutusu;
        TextView txt_zaman_MesajKutusun;

    }
}
