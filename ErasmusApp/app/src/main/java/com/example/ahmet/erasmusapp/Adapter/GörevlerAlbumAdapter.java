package com.example.ahmet.erasmusapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.GorevAdimlari;
import com.example.ahmet.erasmusapp.GoreviBitirenlerListesi;
import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.TabbedActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ahmet on 17.9.2016.
 */
public class GörevlerAlbumAdapter extends  RecyclerView.Adapter<GörevlerAlbumAdapter.MyViewHolder>{
    private Context mContext;
    private List<GörevAlbum1> albumList;
public int görevid;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;
        public ImageView tikonay;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            tikonay =(ImageView)view.findViewById(R.id.onaytik);
        }
    }
    public GörevlerAlbumAdapter(Context mContext, List<GörevAlbum1> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        GörevAlbum1 album = albumList.get(position);


        holder.title.setText(album.getGörevAdi());

        Picasso.with(mContext).load(MyApi.URL_IMAGES+albumList.get(position).getGörevResmi()).resize(300, 300).into(holder.thumbnail);
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  showPopupMenu(holder.overflow);
                PopupMenu popup = new PopupMenu(mContext, holder.overflow);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_album, popup.getMenu());
                görevid = albumList.get(position).getGörevId();
                popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
                    popup.show();
                
            }
        });
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Toast.makeText(mContext, "Resme Tiklandi", Toast.LENGTH_SHORT).show();
                Intent nt = new Intent(mContext.getApplicationContext(), GorevAdimlari.class);
                nt.putExtra("görevid",albumList.get(position).getGörevId());
                // görevid;
                mContext.startActivity(nt);
            }
        });
    }
    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
          switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    // Görevi yapanları çek Başka form üzerinde göster
                    Toast.makeText(mContext, "Görevi yapanlar", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, GoreviBitirenlerListesi.class);
                    intent.putExtra("görevid",görevid);
                    mContext.startActivity(intent);
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
    public void clearData() {
        int size = this.albumList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.albumList.remove(0);
            }

            this.notifyItemRangeRemoved(0, size);
        }
    }
}
