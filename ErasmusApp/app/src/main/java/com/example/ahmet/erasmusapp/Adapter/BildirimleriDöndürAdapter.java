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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yenen on 30.10.2016.
 */
public class BildirimleriDöndürAdapter extends ArrayAdapter<BildirimleriDöndürAdapterModel> {
    private Context context;
    private ViewHolder viewHolder;
    private List<BildirimleriDöndürAdapterModel> arrayList = new ArrayList<BildirimleriDöndürAdapterModel>();

    public BildirimleriDöndürAdapter(Context context,  List<BildirimleriDöndürAdapterModel> list_item) {

        super(context, R.layout.fragcitem_yeni,list_item);
        this.context = context;
        this.arrayList = list_item;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.fragcitem_yeni, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.Ark_profilresmi = (ImageView)view.findViewById(R.id.img_profilFragc);
            viewHolder.txt_BoolenKontrol = (TextView)view.findViewById(R.id.boolentxtler);
            viewHolder.txt_zaman = (TextView)view.findViewById(R.id.txt_zaman);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }

        //
        try {

                viewHolder.txt_BoolenKontrol.setText(String.valueOf(arrayList.get(position).getProfilResmi()).toString());
                if (arrayList.get(position).getArkadasEkledin() == true) {
                    viewHolder.txt_BoolenKontrol.setText("Arkadaş ekledin! " + String.valueOf(arrayList.get(position).getKullaniciAdi()).toString() + " arkadaşlık istediğinizi kabul etti.");
                }
                if (arrayList.get(position).getArkadaslıkİsteğiGeldi() == true) {
                    viewHolder.txt_BoolenKontrol.setText(String.valueOf(arrayList.get(position).getKullaniciAdi()).toString() + " arkadaşlık isteği yolladı.");
                }
                if (arrayList.get(position).getBegeni() == true) {
                    viewHolder.txt_BoolenKontrol.setText(String.valueOf(arrayList.get(position).getKullaniciAdi()).toString() + " [ " + arrayList.get(position).getGörevId() + " ] nolu görevinizin [ " + arrayList.get(position).getAdimId() + " ] nolu adımını beğendi.");
                }
                if (arrayList.get(position).getYorum() == true) {
                    viewHolder.txt_BoolenKontrol.setText(String.valueOf(arrayList.get(position).getKullaniciAdi()).toString() + " [ " + arrayList.get(position).getGörevId() + " ] nolu görevinizin [ " + arrayList.get(position).getAdimId() + " ] nolu adımına  yorum yaptı.");
                }
                viewHolder.txt_zaman.setText(arrayList.get(position).getTarihZaman());
/// gelen meseaja göre texti yaz
                Picasso.with(getContext()).load(MyApi.URL_IMAGES + arrayList.get(position).getProfilResmi()).transform(new CircleTransformation()).into(viewHolder.Ark_profilresmi);

        }catch (Exception ex){

        }

        return  view;
    }

    public static class  ViewHolder{

        TextView txt_BoolenKontrol;
        ImageView Ark_profilresmi;
        TextView txt_zaman;
    }
}
