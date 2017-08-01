package com.example.ahmet.erasmusapp.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ahmet.erasmusapp.QrKodKontrolet;
import com.example.ahmet.erasmusapp.FoursquareApi.foursquare;
import com.example.ahmet.erasmusapp.GorevAdimlari;
import com.example.ahmet.erasmusapp.GoreviBitirenlerListesi;
import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.TabbedActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmet on 29.9.2016.
 */
public class GörevAdimlariAdapter extends RecyclerView.Adapter<GörevAdimlariAdapter.MeViewHolder> {
    private ProgressDialog progressDialog;
    private Context mContext;
    private List<GörevAdimlariAModel> adimlist;
    public static int görevid;
    public RecyclerView recyclerView;
    private GörevAdimlariAdapter adapter;
    public ArrayList<String> arrList = new ArrayList<String>();
    public GörevAdimlariAdapter(Context mContext, List<GörevAdimlariAModel> adimlist,int görevid,RecyclerView recyclerView,GörevAdimlariAdapter adapter) {
        this.adapter =adapter;
        this.mContext = mContext;
        this.adimlist = adimlist;
        this.görevid = görevid;
        this.recyclerView = recyclerView;
    }

    public class MeViewHolder extends RecyclerView.ViewHolder {
        public TextView adimtitle, durum;
        public ImageView adimresim;
        public Button burda;

        public ViewPager viewPager;
        public MeViewHolder(View view) {
            super(view);
            viewPager = (ViewPager)view.findViewById(R.id.viewpager_Gorevcard);
            adimtitle = (TextView) view.findViewById(R.id.adimtitle);
            durum = (TextView) view.findViewById(R.id.durum);
            adimresim = (ImageView) view.findViewById(R.id.adimresim);
            burda = (Button) view.findViewById(R.id.burda);

        }
    }

    @Override
    public MeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gorev_card, parent, false);

        return new MeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MeViewHolder holder, final int position) {
       final GörevAdimlariAModel model = adimlist.get(position);
        holder.adimtitle.setText(model.getAdimAdi());
        // Adim Id foursquare apiye yolla
    //    holder.durum.setText(String.valueOf(model.getTamamlanmaDurumu()));
        holder.durum.setText("");
        Picasso.with(mContext).load(MyApi.URL_IMAGES + adimlist.get(position).getAdimResmi()).resize(300, 300).into(holder.adimresim);
        // holder.adimresim(model.getAdimAdi());
        if(model.getTamamlanmaDurumu() == true){
            holder.burda.setEnabled(false);
        }
        holder.burda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position !=3){
                    progressDialog = new ProgressDialog(mContext);
                    progressDialog.setMessage("Bölge Taranıyor...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    foursquare model1 = new foursquare(TabbedActivity._curLat,TabbedActivity._curLng,mContext);
                    model1.servisiDoldur(adimlist.get(position).getAdimAdi(),adimlist.get(position).getAdımId(),görevid);
                    progressDialog.cancel();
                }else if(position == 3){
                    // son adimdir
                        GorevAdimlari adım = new GorevAdimlari();
                         adım.KontrolQrkod(mContext,recyclerView,adapter);
                        GorevAdimlari.Adimid =  adimlist.get(position).getAdımId();
                        GorevAdimlari.Görevid =görevid;

                    Thread beklet = new Thread() {
                        public void run() {
                            try {
                                sleep(15000);
                                QrKodKontrolet qr = new QrKodKontrolet();
                              if (qr.adimikontrolet(görevid).toString().equals("")) {
                                    Log.e("adapterqrdandönen=", "DÖNDÜ");
                                }
                            } catch (Exception e) {
                                Log.e("hata thread", e.toString());
                            }
                        }
                    };beklet.start();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return adimlist.size();
    }

}
