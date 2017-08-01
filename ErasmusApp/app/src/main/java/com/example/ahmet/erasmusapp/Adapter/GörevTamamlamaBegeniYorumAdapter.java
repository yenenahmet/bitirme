package com.example.ahmet.erasmusapp.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.ahmet.erasmusapp.GorevtamamlamabegeniyorumYorumaGec;
import com.example.ahmet.erasmusapp.LoginAct;
import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.TabFragmntlar.FragmentA;
import com.example.ahmet.erasmusapp.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Yenen on 22.10.2016.
 */
public class GörevTamamlamaBegeniYorumAdapter extends ArrayAdapter<GörevTamamlamaBegeniYorumModel> {
private Context context;
    private ViewHolder viewHolder;
    private List<GörevTamamlamaBegeniYorumModel> arraylist = new ArrayList<GörevTamamlamaBegeniYorumModel>();


    public GörevTamamlamaBegeniYorumAdapter(Context context, List<GörevTamamlamaBegeniYorumModel> list_item) {
        super(context, R.layout.gorevtamamlamayeni,list_item);
        this.context = context;
        this.arraylist = list_item;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.gorevtamamlamayeni, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView11 = (TextView)view.findViewById(R.id.textView11);
            viewHolder.profil = (ImageView)view.findViewById(R.id.profil);
            viewHolder.textView12 = (TextView)view.findViewById(R.id.textView12);
            viewHolder.textView13 = (TextView)view.findViewById(R.id.textView13);
            viewHolder.textView14 = (TextView)view.findViewById(R.id.textView14);
            viewHolder.textView15 = (TextView)view.findViewById(R.id.textView15);
            viewHolder.btnBegeni =(ImageView) view.findViewById(R.id.btnBegeniYolla);
            viewHolder.btnBegeniGerial =(ImageView)view.findViewById(R.id.btnbegeniGerial);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.textView11.setText(arraylist.get(position).getKullaniciAdi());
        Picasso.with(getContext()).load((MyApi.URL_IMAGES+arraylist.get(position).getProfilResmi())).transform(new CircleTransformation()).into(viewHolder.profil);
        viewHolder.textView12.setText(arraylist.get(position).getGörevAdi());
        viewHolder.textView13.setText(arraylist.get(position).getAdimAdi());
        viewHolder.textView14.setText(String.valueOf(arraylist.get(position).getBegeniSayisi()).toString());
        viewHolder.textView15.setText(String.valueOf(arraylist.get(position).getYorumSayisi()).toString());
        if(String.valueOf(LoginAct.userid).toString().equals(arraylist.get(position).getBegenildimi())){
            viewHolder.btnBegeniGerial.setVisibility(View.VISIBLE);
            viewHolder.btnBegeni.setVisibility(View.INVISIBLE);
        }else {
            viewHolder.btnBegeni.setVisibility(View.VISIBLE);
            viewHolder.btnBegeniGerial.setVisibility(View.INVISIBLE);
        }

        viewHolder.btnBegeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // MyUserid + GönderininUserid Gönderilcek
                                          // Ayrıca GönderiniUserına BildirimGidicek

              FragmentA gg = new FragmentA();
              gg.begeniyolla(arraylist.get(position).getAdımId(),arraylist.get(position).getGörevId(),arraylist.get(position).getUserId(),context);
            }
        });


        viewHolder.btnBegeniGerial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA ga = new FragmentA();
                ga.BegeniGeriAl(arraylist.get(position).getAdımId(),arraylist.get(position).getGörevId(),arraylist.get(position).getUserId(),context);

            }
        });
        return  view;
    }
    public static class  ViewHolder{
        ImageView profil;
        TextView textView11;//ism
        TextView textView12;//GörevADi
        TextView textView13;
        ImageView imageView2; // GörevImage
        ImageView imageView3; //AdimImage
        TextView textView14; // begeni
        TextView textView15; //yorum
        ImageView btnBegeni;
        ImageView btnBegeniGerial;
    }
}
