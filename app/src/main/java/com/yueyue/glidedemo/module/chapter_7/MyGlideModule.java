package com.yueyue.glidedemo.module.chapter_7;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

import okhttp3.OkHttpClient;


/**
 * author : yueyue on 2018/5/11 15:08
 * desc   : Android图片加载框架最全解析（七），实现带进度的Glide图片加载功能 - CSDN博客 https://blog.csdn.net/guolin_blog/article/details/78357251
 * 注意1:在Glide4.0中已经废弃了GlideModule,因为这种形式需要AndroidManifest中配置,但Glide4.0中已经推荐使用AppGlideModule
 * 暂时关闭了该GlideModule,需要开启麻烦前往AndroidManifest开启
 */
public class MyGlideModule implements GlideModule {

    private static final String TAG = MyGlideModule.class.getSimpleName();

    //InternalCacheDiskCacheFactory和ExternalCacheDiskCacheFactory的默认硬盘缓存大小都是250M。
    //也就是说，如果你的应用缓存的图片总大小超出了250M，那么Glide就会按照DiskLruCache算法的原则来清理缓存的图片
    private static final int DISK_CACHE_SIZE = 500 * 1024 * 1024;


    /**
     * 更改Glide配置
     */
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        //这log主要用于查看该类MyGlideModule是否起作用(就是Glide是否采用了这里的配置)
        Log.i(TAG, "applyOptions: " + builder.toString());
        //设置外部存储,并且指定最大缓存为500M(默认缓存为250M)

        //ExternalCacheDiskCacheFactory已经失效,换为ExternalPreferredCacheDiskCacheFactory
//        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, DISK_CACHE_SIZE));
        builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, DISK_CACHE_SIZE));

        //Glide4默认的图片质量为ARGB_8888,Glide3的为PREFER_RGB_565
        //下面这种设置图片解码格式在Glide4已经过时,已经换成在Option设置,即requestOptions.format(DecodeFormat.PREFER_RGB_565);
        //builder.setDecodeFormat(DecodeFormat.PREFER ARGB_8888);


    }

    /**
     * 替换Glide组件,一般这里是讲Glide默认使用的HttpURLConnection替换成OkHttp
     */
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //添加拦截器 https://www.jianshu.com/p/1c39c7bb34ca
        builder.addInterceptor(new ProgressInterceptor());
        OkHttpClient okHttpClient = builder.build();
        //这里glide.register在Glide4之后失效了
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpGlideUrlLoader.Factory(okHttpClient));
    }
}
