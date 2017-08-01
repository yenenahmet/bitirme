package com.example.ahmet.erasmusapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmet.erasmusapp.LoginAct;
import com.example.ahmet.erasmusapp.Model.MesajÖzelDenemeModel;
import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.OzelMesaj;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.SQL_Veritabanı_MesajModel;
import com.example.ahmet.erasmusapp.TabFragmntlar.FragmentD;
import com.example.ahmet.erasmusapp.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yenen on 8.3.2017.
 */
public class ÖzelmesajDenemeAdapter  extends ArrayAdapter<SQL_Veritabanı_MesajModel> {
    private Context context;
    private ViewHolder viewHolder;
    private List<SQL_Veritabanı_MesajModel> arrayList = new ArrayList<SQL_Veritabanı_MesajModel>();

    public ÖzelmesajDenemeAdapter(Context context, List<SQL_Veritabanı_MesajModel> list_item) {
        super(context, R.layout.ozelmesajitem,list_item);
        this.context =context;
        this.arrayList =list_item;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.ozelmesajitem, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.Kullanici_adi_özelmesaj = (TextView)view.findViewById(R.id.Kullanici_adi_özelmesaj);
            viewHolder.img_profil_özelmesaj = (ImageView)view.findViewById(R.id.img_profil_Özelmesaj);
            viewHolder.özelmesaj = (TextView)view.findViewById(R.id.özelmesaj);
            viewHolder.txt_zaman_özelmesaj = (TextView)view.findViewById(R.id.txt_zaman_özelmesaj);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.Kullanici_adi_özelmesaj.setText(arrayList.get(position).getKullanıcıAdi());
        viewHolder.özelmesaj.setText(arrayList.get(position).getMesaj());
        viewHolder.txt_zaman_özelmesaj.setText(arrayList.get(position).getTarihZaman());
       if(arrayList.get(position).getKullanıcıAdi().equals(FragmentD.Ad)){
            Picasso.with(getContext()).load(MyApi.URL_IMAGES+ FragmentD.profilresmi1).transform(new CircleTransformation()).into(viewHolder.img_profil_özelmesaj);
        }else{
           Picasso.with(getContext()).load(MyApi.URL_IMAGES+ OzelMesaj.profilresmBaska).transform(new CircleTransformation()).into(viewHolder.img_profil_özelmesaj);
       }
        return  view;
    }
    public static class  ViewHolder{
        TextView Kullanici_adi_özelmesaj;
        TextView özelmesaj;
        ImageView img_profil_özelmesaj;
        TextView txt_zaman_özelmesaj;
    }
}
