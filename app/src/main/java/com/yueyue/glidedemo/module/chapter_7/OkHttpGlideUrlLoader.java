package com.yueyue.glidedemo.module.chapter_7;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * author : yueyue on 2018/5/12 11:43
 * * desc   : 这里仿照 {@link com.bumptech.glide.load.model.stream.HttpGlideUrlLoader}
 * Android图片加载框架最全解析（六），探究Glide的自定义模块功能 - CSDN博客 https://blog.csdn.net/guolin_blog/article/details/78179422
 */
public class OkHttpGlideUrlLoader implements ModelLoader<GlideUrl, InputStream> {

    private OkHttpClient okHttpClient;

    public OkHttpGlideUrlLoader(OkHttpClient client) {
        this.okHttpClient = client;
    }

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(@NonNull GlideUrl glideUrl, int width, int height,
                                               @NonNull Options options) {
        return new LoadData<>(glideUrl, new OkHttpFetcher(okHttpClient, glideUrl));
    }

    @Override
    public boolean handles(@NonNull GlideUrl glideUrl) {
        return false;
    }

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        private final static int CONNECT_TIMEOUT = 60;
        private final static int READ_TIMEOUT = 100;
        private final static int WRITE_TIMEOUT = 60;

        private OkHttpClient client;

        public Factory() {
        }

        public Factory(OkHttpClient client) {
            this.client = client;
        }


        private OkHttpClient getOkHttpClient() {
            //DCL
            if (client == null) {
                synchronized (OkHttpClient.class) {
                    if (client == null) {
                        client = initOkHttp();
                    }
                }
            }
            return client;
        }


        private OkHttpClient initOkHttp() {
            //okhttp设置超时时间 - CSDN博客 https://blog.csdn.net/rodulf/article/details/51363295
            return new OkHttpClient.Builder()
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)//设置写的超时时间
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                    .build();
        }


        @NonNull
        @Override
        public ModelLoader<GlideUrl, InputStream> build(@NonNull MultiModelLoaderFactory multiFactory) {
            return new OkHttpGlideUrlLoader(getOkHttpClient());
        }


        @Override
        public void teardown() {

        }
    }
}
