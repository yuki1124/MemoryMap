package com.lifeistech.android.myapplication;

import android.Manifest;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.annotation.NonNull;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements LocationListener, OnMapReadyCallback {

    LocationManager locationManager;
    private Marker mMarker = null;
    private GoogleMap mMap;
    private Location mLocation;
    //google map




    //public Realm realm;
    //realm

    ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Sticker.from(R.drawable.DROP)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000, 50, this);
        //google map

        Button buttonOpenSheet = findViewById(R.id.stickers);
        buttonOpenSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(),"bottomsheet");
                bottomSheet.setOnStickerClickListener(new BottomSheetDialog.OnStickerClickListener() {
                    @Override
                    public void onClick(Sticker sticker) {
                        android.app.AlertDialog.Builder mBuilder=new android.app.AlertDialog.Builder(MainActivity.this);
                        View mView =getLayoutInflater().inflate(R.layout.dialog_memo, null);
                        mBuilder.setView(mView);
                        android.app.AlertDialog dialog=mBuilder.create();
                        dialog.show();
                        Button mSave=(Button)mView.findViewById(R.id.save);
                        mSave.setOnClickListener(new View.OnClickListener(){

                            @Override
                            public void onClick(View v) {

                            }
                        });
                        // ダイアログを表示
                        // 緯度・経度・スタンプの種類・日にち
                        // 場所とメモを書けるダイアログを表示する

                        // 緯度経度
                        // mLocation.latitude, mLocation.longitude

                        // ステッカーの種類
                        // sticker.name()

                        // 日にち
                        // メモ教科書を見てください

                        // 場所とメモ
                        // 動画と同じ方法でとれる

                        // Realmの保存
                        // メモ教科書みて
                    }
                });
            }
        });
        //シールが出てくる
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]permissions, @NonNull int[] grantResults){}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras){}

    @Override
    public void onLocationChanged(Location location){
        Log.d("debug","Latitude:"+location.getLatitude());
        Log.d("debug","Longitude:"+location.getLongitude());

        mLocation = location;

        if(mMarker!=null){
            mMarker.remove();
        }

        LatLng Tokyo = new LatLng(location.getLatitude(), location.getLongitude());
        // Markerを作ってくれる
        mMarker = mMap.addMarker(new MarkerOptions().position(Tokyo).title("Marker in Tokyo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Tokyo));
    }

    @Override
    public void onProviderEnabled(String provider){}

    @Override
    public void onProviderDisabled(String provider){}



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera

    }

}
