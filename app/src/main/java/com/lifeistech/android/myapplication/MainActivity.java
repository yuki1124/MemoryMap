package com.lifeistech.android.myapplication;

import android.Manifest;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.annotation.NonNull;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements LocationListener, OnMapReadyCallback {

    LocationManager locationManager;
    private Marker mMarker = null;
    private GoogleMap mMap;
    private Location mLocation;
    //google map


    public Realm realm;
    GoogleMap map;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary2);

        realm = Realm.getDefaultInstance();

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
                bottomSheet.show(getSupportFragmentManager(), "bottomsheet");
                bottomSheet.setOnStickerClickListener(new BottomSheetDialog.OnStickerClickListener() {
                    @Override
                    public void onClick(final Sticker sticker) {
                        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
                        final View mView = getLayoutInflater().inflate(R.layout.dialog_memo, null);
                        mBuilder.setView(mView);
                        final android.app.AlertDialog dialog = mBuilder.create();
                        dialog.show();
                        Button mSave = (Button) mView.findViewById(R.id.save);
                        mSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String place = ((EditText) mView.findViewById(R.id.EditPlace)).getText().toString();
                                String content = ((EditText) mView.findViewById(R.id.EditContent)).getText().toString();
                                String stickername = sticker.name();
                                Double latitude = mLocation.getLatitude();
                                Double longitude = mLocation.getLongitude();
                                save(place, content, stickername, latitude, longitude);

                                dialog.dismiss();
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

    public void save(final String EditContent, final String EditPlace, final String stickername, final double latitude, final double longitude) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.JAPANESE);
        final String updateDate = sdf.format(date);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Memo memo = realm.createObject(Memo.class);
                memo.content = EditContent;
                memo.place = EditPlace;
                memo.updateDate = updateDate;
                memo.stickername = stickername;
                memo.latitude = latitude;
                memo.longitude = longitude;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }
    //realm

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("debug", "Latitude:" + location.getLatitude());
        Log.d("debug", "Longitude:" + location.getLongitude());

        mLocation = location;

        if (mMarker != null) {
            mMarker.remove();
        }

        LatLng Tokyo = new LatLng(location.getLatitude(), location.getLongitude());
        // Markerを作ってくれる
        mMarker = mMap.addMarker(new MarkerOptions().position(Tokyo).title("Now"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLocation.getLatitude(), mLocation.getLongitude()), 15));

    }

    //realmからデータを取得
    //

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }


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

        RealmResults<Memo> results = realm.where(Memo.class).findAll();
        for (Memo data : results) {
            //data.longitude, data.latitude
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(data.latitude, data.longitude))
                    .title(data.place))
                    .setIcon(BitmapDescriptorFactory.fromResource(Sticker.valueOf(data.stickername).id));
        }
    }
}



