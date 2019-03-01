package com.sunnygroup.backoffice.tdcapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sunnygroup.backoffice.tdcapp.adapters.BVAdapter;
import com.sunnygroup.backoffice.tdcapp.adapters.MachineMovementAdapter;
import com.sunnygroup.backoffice.tdcapp.requests.DataVolleyRequest;

import org.json.JSONObject;

public class BVActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bv);
        mContext = this;

        ImageView backButton = (ImageView) findViewById(R.id.back_image_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ImageView addButton = (ImageView) findViewById(R.id.add_image_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BVFormActivity.class);
                startActivity(intent);
            }
        });

        loadHistory();
    }

    protected void loadHistory () {
        recyclerView = (RecyclerView) findViewById(R.id.bv_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        String url = "http://backoffice.ltd/location/5908f432fefc054108db23c1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        adapter = new BVAdapter(response);

                        try {
                            recyclerView.setAdapter(adapter);
                        } catch (InflateException e) {
                            System.out.println(">>>>>>>>>>>>>>> ERROR " + e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.print(">>>>>>>>>>>>>>>>>>>Error:" + error.toString());
                    }
                });

        DataVolleyRequest.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
