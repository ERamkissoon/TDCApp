package com.sunnygroup.backoffice.tdcapp.requests;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class DataVolleyRequest {
    private static DataVolleyRequest instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context mContext;

    private DataVolleyRequest (Context context) {
        mContext = context;
        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap>
                    cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized DataVolleyRequest getInstance (Context context) {
        if (instance == null) {
            instance = new DataVolleyRequest(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue (Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader () {
        return imageLoader;
    }
}
