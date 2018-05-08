package com.example.medla.ceglocation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

public class SpotActivity extends BaseActivity {

    private MapView mMapView = null;
    private BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_spot);
        //获取地图控件引用
        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_cluster:
                Intent intentC = new Intent(SpotActivity.this, ClusterTest.class);
                startActivity(intentC);
                Toast.makeText(this, "This is ClusterTest Mode", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_bubble:
                Intent intent = new Intent(SpotActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "This is Bubble Display Mode", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_spot:
                Intent intentS = new Intent(SpotActivity.this, SpotActivity.class);
                startActivity(intentS);
                Toast.makeText(this, "This is Spot Display Mode", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_exit:
                Toast.makeText(this, "See you next time", Toast.LENGTH_SHORT).show();
                ActivityCollector.finishAll();
        }
        return true;
    }
}
