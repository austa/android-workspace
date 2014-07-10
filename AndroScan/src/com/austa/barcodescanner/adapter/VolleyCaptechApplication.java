package com.austa.barcodescanner.adapter;

import android.app.Application;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleyCaptechApplication extends Application {

    private ImageLruCache imageCache;
    private RequestQueue queue;
    private ImageLoader imageLoader;

    public void onCreate() {
        imageCache = new ImageLruCache(ImageLruCache.getDefaultLruCacheSize());
        queue = Volley.newRequestQueue(this);
        imageLoader = new ImageLoader(queue, imageCache);
    }

    public ImageLruCache getCache() {
        return imageCache;
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

}
