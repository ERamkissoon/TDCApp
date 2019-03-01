package com.sunnygroup.backoffice.tdcapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sunnygroup.backoffice.tdcapp.adapters.MachineMovementAdapter;
import com.sunnygroup.backoffice.tdcapp.entities.MachineMovement;
import com.sunnygroup.backoffice.tdcapp.requests.DataVolleyRequest;
import com.sunnygroup.backoffice.tdcapp.viewmodels.MachineMovementViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class MachineMovementActivity extends AppCompatActivity implements Serializable {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private MachineMovementViewModel viewModel;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_movement);
        mContext = this;

        final RecyclerView.Adapter adapter = new MachineMovementAdapter(this);
        viewModel = ViewModelProviders.of(this).get(MachineMovementViewModel.class);
        viewModel.getAllMachineMovements().observe(this, new Observer<List<MachineMovement>>() {
            @Override
            public void onChanged(@Nullable final List<MachineMovement> machineMovements) {
                ((MachineMovementAdapter) adapter).setMachineMovementList(machineMovements);
            }
        });

        ImageView backButton = (ImageView) findViewById(R.id.back_image_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


//        ImageView addButton = (ImageView) findViewById(R.id.add_image_button);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, MachineMovementFormActivity.class);
//                startActivity(intent);
//            }
//        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MachineMovementActivity.this, MachineMovementFormActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.machine_movement_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        try {
            recyclerView.setAdapter(adapter);
        } catch (InflateException e) {
            System.out.println(">>>>>>>>>>>>>>> ERROR " + e.toString());
        }

        //loadHistory();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            MachineMovement movement = (MachineMovement) data.getSerializableExtra(MachineMovementFormActivity.EXTRA_REPLY);
            viewModel.insert(movement);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.app_name,
                    Toast.LENGTH_LONG).show();
        }
    }

//    protected void loadHistory () {
//        recyclerView = (RecyclerView) findViewById(R.id.machine_movement_recycler_view);
//        recyclerView.setHasFixedSize(true);
//
//        layoutManager = new LinearLayoutManager(mContext);
//        recyclerView.setLayoutManager(layoutManager);
//
////        String url = "http://backoffice.ltd/location/5908f432fefc054108db23c1";
//        String url = "http://192.168.10.124:8888/movement/machine/all";
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        System.out.println(">>>>>>>>>>>> SERVER RESPONSE: " + response);
//                        adapter = new MachineMovementAdapter(mContext);
//
//                        try {
//                            recyclerView.setAdapter(adapter);
//                        } catch (InflateException e) {
//                            System.out.println(">>>>>>>>>>>>>>> ERROR " + e.toString());
//                        }
//                    }
//                }, new Response.ErrorListener() {
////                    @Override
////                    public void onErrorResponse(VolleyError error) {
////                        System.out.print(">>>>>>>>>>>>>>>>>>> > Error:" + error.toString());
////                    }
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        String message = null;
//
//                        System.out.println(">>>>>>>>>>>>>>>>>>> > Error:" + volleyError.toString());
//                        if (volleyError instanceof NetworkError) {
//                            message = "Cannot connect to Internet...Please check your connection!";
//                        } else if (volleyError instanceof ServerError) {
//                            message = "The server could not be found. Please try again after some time!!";
//                        } else if (volleyError instanceof AuthFailureError) {
//                            message = "Cannot connect to Internet...Please check your connection!";
//                        } else if (volleyError instanceof ParseError) {
//                            message = "Parsing error! Please try again after some time!!";
//                        } else if (volleyError instanceof NoConnectionError) {
//                            message = "Cannot connect to Internet...Please check your connection!";
//                        } else if (volleyError instanceof TimeoutError) {
//                            message = "Connection TimeOut! Please check your internet connection.";
//                        }
//                        System.out.println(">>>>>>>>>>>>>>>>>>> > Error: " + message);
//                    }
//                });
//
//        DataVolleyRequest.getInstance(this).addToRequestQueue(jsonArrayRequest);
//    }

}
