package com.hunter.appstreetassignment.simplegallerycaching;

import android.app.ActivityManager;
import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH);
        buildGlide();
    }

    private void buildGlide(){
        GlideBuilder gb = new GlideBuilder();
        LruResourceCache lruMemCache = new LruResourceCache(getMemCacheSize(8));
        gb.setMemoryCache(lruMemCache);
        InternalCacheDiskCacheFactory diskCacheFactory = new InternalCacheDiskCacheFactory(this, 300);
        gb.setDiskCache(diskCacheFactory);
        LruBitmapPool bitmapPool = new LruBitmapPool(getMemCacheSize(8)/10);
        gb.setBitmapPool(bitmapPool);
        //set custom Glide as global singleton
        Glide.init(gb.build(this));
    }

    private int getMemCacheSize(int percent){
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ((ActivityManager)
                getSystemService(ACTIVITY_SERVICE)).getMemoryInfo(mi);
        double availableMemory= mi.availMem;
        return (int)(percent*availableMemory/100);
    }

}
