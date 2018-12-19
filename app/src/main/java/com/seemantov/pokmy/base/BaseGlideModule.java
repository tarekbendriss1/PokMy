package com.seemantov.pokmy.base;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;

import okhttp3.OkHttpClient;

@GlideModule
public final class BaseGlideModule extends AppGlideModule {

    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024; // 10MB
    // @Inject OkHttpClient okHttpClient;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();
        builder.setMemoryCache(new LruResourceCache(DISK_CACHE_SIZE));
        builder.setDefaultRequestOptions(requestOptions);
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        //   App.getDataComponent().inject(this);
        //registry.append(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());

        // ************** kén hédha ***************
        // registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
        // ************** kén hédha ***************


        OkHttpClient okHttpClient= UnsafeOkHttpClient.getUnsafeOkHttpClient();
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(okHttpClient));
    }

    // Disable manifest parsing to avoid adding similar modules twice.
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
