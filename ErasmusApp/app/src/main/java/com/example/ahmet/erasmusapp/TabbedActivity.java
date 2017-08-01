package com.example.ahmet.erasmusapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmet.erasmusapp.FoursquareApi.foursquare;
import com.example.ahmet.erasmusapp.Socket_Servis.Cevrimici_Kontrol_Servis_Socket;
import com.example.ahmet.erasmusapp.TabFragmntlar.FragmentA;
import com.example.ahmet.erasmusapp.TabFragmntlar.FragmentB;
import com.example.ahmet.erasmusapp.TabFragmntlar.FragmentC;
import com.example.ahmet.erasmusapp.TabFragmntlar.FragmentD;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class TabbedActivity extends AppCompatActivity {
    private LocationManager locationManager;
    public static boolean SocketDurumu = true;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public static double _curLat, _curLng;
    public static final int zaman = 1000 * 60;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);
        useridTanımla(getApplicationContext());
        SocketDurumu = true;
        Intent intt = new Intent(getApplicationContext(), Cevrimici_Kontrol_Servis_Socket.class);
        startService(intt);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();
        Log.e("Token: ", FirebaseInstanceId.getInstance().getToken());

        MyFirebaseCloud fcm = new MyFirebaseCloud();
        fcm.sendRegistrationToServer(FirebaseInstanceId.getInstance().getToken());
        Wifiac();
        KonumKontrol();
        LocationManager locationManager1 = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location l1 = locationManager1.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (l1 != null) {
            _curLat = l1.getLatitude();
            _curLng = l1.getLongitude();
        }
        Log.e("konnum",String.valueOf(_curLat)+","+String.valueOf(_curLng));

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    NetworkProvider();
                } else {
                    // permission denied
                    KonumKontrol();
                }
                break;
        }
    }
    private void KonumKontrol(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            Log.e("asdasdas", "chek ilk if");
        } else {
            NetworkProvider();
            Log.e("CurrenCalisti", "gpsCofig");
        }
    }
    private void NetworkProvider(){
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        return;
    }
    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 0, new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            String currentLat = String.valueOf(location.getLatitude());
            String currentLot = String.valueOf(location.getLongitude());
            TabbedActivity._curLat =location.getLatitude();
            TabbedActivity._curLng =location.getLongitude();

            Log.e("Curren= ", currentLat + "," + currentLot);

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    });
}

    public static void useridTanımla(Context mcontext){
    preferences = PreferenceManager.getDefaultSharedPreferences(mcontext);
   editor = preferences.edit();
    if(preferences.getBoolean("login", false)){
        LoginAct.userid = preferences.getInt("userid",0);
        Log.e("userid",String.valueOf(LoginAct.userid).toString());
    }else{
        Log.e("Userid-Atama",String.valueOf(LoginAct.userid).toString());
        Toast.makeText(mcontext,"Xml Tanımlaması yapılamadı...",Toast.LENGTH_LONG).show();
    }
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tabbed, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int i) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment fragment = null;

            if (i == 0) {
                fragment = new FragmentA();
            }
            if (i == 1) {
                fragment = new FragmentB();
            }
            if (i == 2) {
                fragment = new FragmentC();
            }
            if(i== 3){
                fragment = new FragmentD();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Anasayfa";
                case 1:
                    return "Görevler";
                case 2:
                    return "Bildirim";
                case 3:
                    return "Profil";
            }
            return null;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Sayfadan Çıkıldı","OnDestroy Tabbed");
        SocketDurumu =false;

    }


            public void Wifiac(){
                WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
                boolean enabled = wifiManager.setWifiEnabled(true);
                Log.e("wifiDurum", String.valueOf(enabled));
            }
}
