package com.example.ahmet.erasmusapp.Adapter;

/**
 * Created by Yenen on 8.2.2017.
 */
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ahmet.erasmusapp.MyApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Yenen on 21.1.2017.
 */
public class AndroidImageAdapter extends PagerAdapter {
    Context mContext;
    ArrayList<String> arrayList;
    public AndroidImageAdapter(Context context, ArrayList<String> arrayList) {
        this.mContext = context;
        this.arrayList =arrayList;
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //     mImageView.setImageResource(sliderImagesId[i]);
        Picasso.with(mContext).load(MyApi.URL_IMAGES +arrayList.get(i)).into(mImageView);

        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}