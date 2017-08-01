package com.example.ahmet.erasmusapp.Adapter;

import android.content.Context;
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
 * Created by Yenen on 2.11.2016.
 */
public class GöreviBitirenlerAdapter  extends ArrayAdapter<GöreviBitirenlerAdapterModel> {
    private Context context;
    private ViewHolder viewHolder;
    private List<GöreviBitirenlerAdapterModel> arrayList = new ArrayList<GöreviBitirenlerAdapterModel>();

    public GöreviBitirenlerAdapter(Context context,  List<GöreviBitirenlerAdapterModel> list_item) {

        super(context, R.layout.arklistesiitem_layout,list_item);
        this.context = context;
        this.arrayList = list_item;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.arklistesiitem_layout, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txt_name = (TextView)view.findViewById(R.id.Kullanici_adi);
            viewHolder.Ark_profilresmi = (ImageView)view.findViewById(R.id.img_profil);
            viewHolder.txt_ArkuserId = (TextView)view.findViewById(R.id.Ark_id);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.txt_name.setText(arrayList.get(position).getKullaniciAdi());

        Picasso.with(getContext()).load(MyApi.URL_IMAGES+arrayList.get(position).getProfilResmi()).resize(115,115).transform(new CircleTransformation()).into(viewHolder.Ark_profilresmi);

        return  view;
    }
    public static class  ViewHolder{
        TextView txt_name;
        TextView txt_ArkuserId;
        ImageView Ark_profilresmi;

    }
}
