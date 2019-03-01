package com.sunnygroup.backoffice.tdcapp.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.sunnygroup.backoffice.tdcapp.daos.MachineMovementDao;
import com.sunnygroup.backoffice.tdcapp.entities.MachineMovement;
import com.sunnygroup.backoffice.tdcapp.requests.DataVolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Database(entities = {MachineMovement.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MachineMovementDao machineMovementDao();

    private static volatile AppDatabase INSTANCE;
    private static Context ctx;

    public static AppDatabase getDatabase(final Context context) {
        ctx = context;
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "local_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static RoomDatabase.Callback sRoomDatabaseCallback =
        new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MachineMovementDao machineMovementDao;

        PopulateDbAsync(AppDatabase db) {
            machineMovementDao = db.machineMovementDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {


            String url = "http://192.168.10.124:8888/movement/machine/all";

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                machineMovementDao.deleteAll();

                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    MachineMovement movement = new MachineMovement(jsonObject);
                                    machineMovementDao.insert(movement);
                                }
                            } catch (JSONException e) {
                                System.out.println(e.toString());
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println(">>>>>>>>>>>>>>>>>>> > Error:" + volleyError.toString());
                        }
                    });

            DataVolleyRequest.getInstance(ctx.getApplicationContext()).addToRequestQueue(jsonArrayRequest);

            /*
            MachineMovement movement = new MachineMovement("AMUSEMENT_MACHINE", "00003",
                    "SN969NI7VCSXN", 1);
            movement.setLocationFromID(1);
            movement.setLocationToID(2);
            machineMovementDao.insert(movement);
            movement = new MachineMovement("AMUSEMENT_MACHINE", "00003",
                    "SN969NI7VCSXN", 1);
            movement.setLocationFromID(2);
            movement.setLocationToID(1);
            machineMovementDao.insert(movement);
            */
            return null;
        }
    }
}
