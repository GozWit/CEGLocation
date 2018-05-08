package com.example.medla.ceglocation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
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
                Intent intentC = new Intent(this, ClusterTest.class);
                startActivity(intentC);
                Toast.makeText(this, "This is ClusterTest Mode", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_bubble:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "This is Bubble Display Mode", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_spot:
                Intent intentS = new Intent(this, SpotActivity.class);
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
