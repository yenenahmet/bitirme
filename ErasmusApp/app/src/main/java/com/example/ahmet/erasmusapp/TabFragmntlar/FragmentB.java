package com.example.ahmet.erasmusapp.TabFragmntlar;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ahmet.erasmusapp.Adapter.GörevAlbum1;
import com.example.ahmet.erasmusapp.Adapter.GörevlerAlbumAdapter;
import com.example.ahmet.erasmusapp.Model.GörevlerEtap1Model;
import com.example.ahmet.erasmusapp.MyApi;
import com.example.ahmet.erasmusapp.R;
import com.example.ahmet.erasmusapp.Service.GörevlerEtap1Service;
import com.example.ahmet.erasmusapp.TabbedActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Ahmet on 5.9.2016.
 */
public class FragmentB extends Fragment {

    private List<GörevAlbum1> list = new ArrayList<GörevAlbum1>();

    private RecyclerView recyclerView;
    private GörevlerAlbumAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list.clear();
        // TODO Auto-generated method stub
        View view=inflater.inflate(R.layout.fragment_b, container, false);
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        TabbedActivity.useridTanımla(getActivity());
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        //setSupportActionBar(toolbar);
     //  initCollapsingToolbar();
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });

            recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
            adapter = new GörevlerAlbumAdapter(getActivity(), list);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            recyclerView.setAdapter(adapter);



        GörevDoldur();/////
        try {
            Glide.with(this).load(R.drawable.timthumb).into((ImageView) view.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }

private void GörevDoldur(){

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MyApi.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    GörevlerEtap1Service servis = retrofit.create(GörevlerEtap1Service.class);
    Call<GörevlerEtap1Model[]> call = servis.getGörevlerAlbum();
    call.enqueue(new Callback<GörevlerEtap1Model[]>() {
        @Override
        public void onResponse(Response<GörevlerEtap1Model[]> response, Retrofit retrofit) {
            if(response.isSuccess()){
                for(GörevlerEtap1Model modelvalues:response.body()){
                    GörevAlbum1 items = new GörevAlbum1();
                    items.setGörevId(modelvalues.GörevId);
                    items.setGörevAdi(modelvalues.GörevAdi);
                    items.setGörevResmi(modelvalues.GörevResmi);
                    list.add(items);

                }
                adapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(), "Hata oluştu!!!", Toast.LENGTH_LONG).show();

            }

        }
        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(getActivity(), "İnternet Bağlantınızda sorun var!!!", Toast.LENGTH_LONG).show();
        }
    });
}
        public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            int position = parent.getChildAdapterPosition(view); // item position

            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}

