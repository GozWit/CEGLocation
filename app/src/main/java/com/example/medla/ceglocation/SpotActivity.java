package com.example.medla.ceglocation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

public class SpotActivity extends BaseActivity {

    private MapView mMapView = null;
    private BaiduMap mBaiduMap;

    public ImportDB dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_spot);

        dbHelper = new ImportDB(this);
        dbHelper.openDatabase();

        //获取地图控件引用
        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        drawSpots();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    public void drawSpots(){

        SQLiteDatabase db = dbHelper.getDatabase();

        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_spot);

        Cursor cursor = db.query("PlayerPosition", null,  null,
                null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                double lat = cursor.getDouble(cursor.getColumnIndex("lat"));
                double lon = cursor.getDouble(cursor.getColumnIndex("lon"));
                LatLng position = new LatLng(lat, lon);
                OverlayOptions option = new MarkerOptions()
                        .position(position)
                        .icon(bitmap);
                mBaiduMap.addOverlay(option);
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

}
