package com.example.ahmet.erasmusapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmet.erasmusapp.Model.BegenenListesiGorModel;
import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yenen on 9.2.2017.
 */
public class BegeniListesiGorAdapter extends ArrayAdapter<BegenenListesiGorAModel> {

    private Context context;
    private ViewHolder viewHolder;
    private List<BegenenListesiGorAModel> arrayList = new ArrayList<BegenenListesiGorAModel>();
    public BegeniListesiGorAdapter(Context context, List<BegenenListesiGorAModel> list_item) {
        super(context, R.layout.begenilistesigor_item, list_item);
        this.context = context;
        this.arrayList = list_item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.begenilistesigor_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_name = (TextView) view.findViewById(R.id.Kullanici_adi_BegniListesigor);
            viewHolder.profilresmi = (ImageView) view.findViewById(R.id.img_profil_BegeniListesiGor);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.txt_name.setText(arrayList.get(position).getKullaniciAdi());
        Picasso.with(getContext()).load(MyApi.URL_IMAGES + arrayList.get(position).getProfilResmi()).resize(125, 125).transform(new CircleTransformation()).into(viewHolder.profilresmi);
        return view;
    }
    public static class ViewHolder {
        TextView txt_name;
        ImageView profilresmi;
    }
}