package com.sunnygroup.backoffice.tdcapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.sunnygroup.backoffice.tdcapp.adapters.IconAdapter;

public class AppDrawerActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_drawer);
        mContext = this;

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new IconAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(AppDrawerActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                runIntent(position);
            }
        });
    }

    private void runIntent (int position) {
        Intent intent = new Intent (mContext, mActivities[position]);
        startActivity(intent);
    }

    private Class[] mActivities = {
            MachineMovementActivity.class, PartMovementActivity.class,
            BVActivity.class, BoardConversionActivity.class,
            PrintActivity.class, HistoryActivity.class,
            SettingsActivity.class
    };
}
