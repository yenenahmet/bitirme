package com.example.ahmet.erasmusapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.LoginAct;
import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yenen on 29.11.2016.
 */
public class PostYorumlarıGetirAdapater extends ArrayAdapter<PostYorumlarıGetirAdapaterModel> {
    private Context context;
    private ViewHolder viewHolder;
    private List<PostYorumlarıGetirAdapaterModel> arrayList = new ArrayList<PostYorumlarıGetirAdapaterModel>();

    public PostYorumlarıGetirAdapater(Context context, List<PostYorumlarıGetirAdapaterModel> list_item) {
        super(context, R.layout.postyorumlarigetir_item_yeni,list_item);
        this.context =context;
        this.arrayList =list_item;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.postyorumlarigetir_item_yeni, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.Kullanici_adi_yorum = (TextView)view.findViewById(R.id.Kullanici_adi_yorum);
            viewHolder.img_profil_Yorum = (ImageView)view.findViewById(R.id.img_profil_Yorum);
            viewHolder.yorum_Yorumlargetir = (TextView)view.findViewById(R.id.yorum_Yorumlargetir);
            viewHolder.txt_zaman_yorum = (TextView)view.findViewById(R.id.txt_zaman_yorum);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.Kullanici_adi_yorum.setText(arrayList.get(position).getYorumYapanAdi());
        viewHolder.yorum_Yorumlargetir.setText(arrayList.get(position).getYorum());
        viewHolder.txt_zaman_yorum.setText(arrayList.get(position).getYorumAtilanTarih());
        Picasso.with(getContext()).load(MyApi.URL_IMAGES+arrayList.get(position).getYorumyapanProfilResmi()).resize(115,115).transform(new CircleTransformation()).into(viewHolder.img_profil_Yorum);

        return  view;
    }
    public static class  ViewHolder{
        TextView Kullanici_adi_yorum;
        TextView yorum_Yorumlargetir;
        ImageView img_profil_Yorum;
        TextView txt_zaman_yorum;
    }
}
