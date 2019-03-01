package com.sunnygroup.backoffice.tdcapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.InflateException;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sunnygroup.backoffice.tdcapp.adapters.MachineMovementAdapter;
import com.sunnygroup.backoffice.tdcapp.entities.MachineMovement;
import com.sunnygroup.backoffice.tdcapp.requests.DataVolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MachineMovementFormActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private Spinner locationSpinnerFrom;
    private Spinner locationSpinnerTo;
    private Spinner machine;
    private Spinner boardType;

    private EditText assetNumber;
    private EditText serialNumber;
    private EditText hardMeterIn;
    private EditText hardMeterOut;
    private EditText softMeterIn;
    private EditText softMeterOut;
    private EditText boardPercentage;
    private EditText maxPayout;
    private EditText denomination;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_movement_form);
        mContext = this;

        ImageView backButton = (ImageView) findViewById(R.id.back_image_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageView saveButton = (ImageView) findViewById(R.id.save_image_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSave();
            }
        });

        locationSpinnerFrom = (Spinner) findViewById(R.id.location_from);
        locationSpinnerTo = (Spinner) findViewById(R.id.location_to);
        machine = (Spinner) findViewById(R.id.company_asset_number);
        boardType = (Spinner) findViewById(R.id.board_type);

        serialNumber = (EditText) findViewById(R.id.serial_number);
        hardMeterIn = (EditText) findViewById(R.id.hard_meter_in);
        hardMeterOut = (EditText) findViewById(R.id.hard_meter_out);
        softMeterIn = (EditText) findViewById(R.id.soft_meter_in);
        softMeterOut = (EditText) findViewById(R.id.soft_meter_out);
        boardPercentage = (EditText) findViewById(R.id.board_percentage);
        maxPayout = (EditText) findViewById(R.id.maximum_payout);
        denomination = (EditText) findViewById(R.id.denomination);
    }

    public void attemptSave() {
        Intent replyIntent = new Intent();
        if (!TextUtils.isEmpty(serialNumber.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
//            MachineMovement movement = new MachineMovement(
//                    boardType.getSelectedItem().toString(),
//                    machine.getSelectedItem().toString(),
//                    serialNumber.getText().toString(),
//                    1);
//
//            movement.setLocationFromID(Integer.parseInt(locationSpinnerFrom.getSelectedItem().toString()));
//            movement.setLocationToID(Integer.parseInt(locationSpinnerTo.getSelectedItem().toString()));
//            movement.setHardMeterIN(Integer.parseInt(hardMeterIn.getText().toString()));
//            movement.setHardMeterOUT(Integer.parseInt(hardMeterOut.getText().toString()));
//            movement.setSoftMeterIN(Integer.parseInt(softMeterIn.getText().toString()));
//            movement.setSoftMeterOUT(Integer.parseInt(softMeterOut.getText().toString()));
//            movement.setBoardPercentage(Integer.parseInt(boardPercentage.getText().toString()));
//            movement.setMaxPayout(Integer.parseInt(maxPayout.getText().toString()));
//            movement.setDenom(Integer.parseInt(denomination.getText().toString()));

            


            MachineMovement movement = new MachineMovement(
                    "AMUSEMENT_MACHINE",
                    "00008",
                    "SN8239OSDFNALLL",
                    1);

            movement.setLocationFromID(1);
            movement.setLocationToID(2);
            movement.setHardMeterIN(000000);
            movement.setHardMeterOUT(000000);
            movement.setSoftMeterIN(000000);
            movement.setSoftMeterOUT(000000);
            movement.setBoardPercentage(50);
            movement.setMaxPayout(5000);
            movement.setDenom(1);

            replyIntent.putExtra(EXTRA_REPLY, movement);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }

//    public void attemptSave() {
////        serialNumber.setError(null);
////        hardMeterIn.setError(null);
////        hardMeterOut.setError(null);
////        softMeterIn.setError(null);
////        softMeterOut.setError(null);
////        boardPercentage.setError(null);
////        maxPayout.setError(null);
////        denomination.setError(null);
////
////        String _serialNumber = serialNumber.getText().toString();
////        String _boardPercentage = boardPercentage.getText().toString();
////        Integer _hardMeterIn = Integer.parseInt(hardMeterIn.getText().toString());
////        Integer _hardMeterOut = Integer.parseInt(hardMeterOut.getText().toString());
////        Integer _softMeterIn = Integer.parseInt(softMeterIn.getText().toString());
////        Integer _softMeterOut = Integer.parseInt(softMeterOut.getText().toString());
////        Integer _maxPayout = Integer.parseInt(maxPayout.getText().toString());
////        Integer _denom = Integer.parseInt(denomination.getText().toString());
////
////
////        boolean cancel = false;
////        View focusView = null;
//
//        RequestQueue queue = Volley.newRequestQueue(mContext);
//        String url = "http://192.168.2.144:8888/tdc/name";
//        url = "http://192.168.2.144:8888/tdc/post";
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        Map<String, Object> location = new HashMap<String, Object>();
//        //params.put("id", "11");
//        params.put("Content-Type", "application/json");
//        params.put("name", "YamatoMebdiz");
//
//        location.put("name", "Saudi");
//        params.put("location", new JSONObject(location));
//
//        JSONObject jsonObject = new JSONObject(params);
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
//                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>ERROR::" + error.toString());
//                    }
//                });
//
//        queue.add(jsonObjectRequest);
////        if (!TextUtils.isEmpty()) {
////
////        }
//
//
//        System.out.println("TESTING");
//    }
}
