package com.seemantov.pokmy.utils;

/**
 * Created by Tarek Ben Driss on 22/03/2018.
 */

import android.graphics.Bitmap;
import android.util.LruCache;

import java.util.ArrayList;
import java.util.List;


public class Cache {
    static int cacheSize = 100 * 1024 * 1024;
    public static int totalun=0;
    public static int totaldeux=0;
    public static int totaltrois=0;


    public static List<Integer> l = new ArrayList<Integer>();

    public static int fav=0;

    public static int id;
    public static int un=0;
    public static int deux=0;
    public static int trois=0;
    public final static LruCache<String, Bitmap> mMemoryCache = new LruCache<>(cacheSize);

    public Cache()
    {

        if(mMemoryCache==null)
        {
            //mMemoryCache = new LruCache<>(cacheSize);
        }
    }

    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
        else
        {
            deleteBitmapFromMemCache(key);
            mMemoryCache.put(key, bitmap);
        }
        //System.out.println("cache = "+mMemoryCache.size());

    }




    public static Bitmap getBitmapFromMemCache(String key) {
        //System.out.println("cache = "+mMemoryCache.size());

        return mMemoryCache.get(key);
    }

    public static void deleteBitmapFromMemCache(String key)
    {
        mMemoryCache.remove(key);
    }


}
