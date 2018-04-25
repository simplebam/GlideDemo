package com.yueyue.glidedemo.network;

import android.util.Log;

import com.yueyue.glidedemo.base.App;
import com.yueyue.glidedemo.base.Constant;
import com.yueyue.glidedemo.model.YingPic;
import com.yueyue.glidedemo.network.api.YingApi;
import com.yueyue.glidedemo.utils.RxUtil;
import com.yueyue.glidedemo.utils.ToastUtil;
import com.yueyue.glidedemo.utils.Util;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : yueyue on 2018/4/17 22:02
 * desc   :
 */
public class NetworkService {
    private static final String TAG = NetworkService.class.getSimpleName();

    private static YingApi yingApi = null;
    private static Retrofit sRetrofit = null;
    private static OkHttpClient sOkHttpClient = null;

    private void init() {
        initOkHttp();
        initRetrofit();
        yingApi = sRetrofit.create(YingApi.class);
    }

    private NetworkService() {
        init();
    }

    public static NetworkService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final NetworkService INSTANCE = new NetworkService();
    }


    private static void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 缓存 http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(Constant.NET_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            if (!Util.isNetworkConnected(App.getContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            Response.Builder newBuilder = response.newBuilder();
            if (Util.isNetworkConnected(App.getContext())) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时
                newBuilder.header("Cache-Control", "public, max-age=" + maxAge);
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                newBuilder.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
            }
            return newBuilder.build();
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        sOkHttpClient = builder.build();
    }

    private static void initRetrofit() {
        sRetrofit = new Retrofit.Builder()
                .baseUrl(YingApi.HOST)
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())  //设置 Json 转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())   //RxJava 适配器
                .build();
    }

    /**
     * 其实cancel网络请求的时候，如果还未和服务器建立连接，它会回调到onFailure()方法中，
     * 还有一种情况就是它会在onResponse的时候刚好cancel网络请求，那么它会在onResponse()方法中抛出java.net.SocketException: Socket closed
     */
    private static Consumer<Throwable> disposeFailureInfo(Throwable t) {
        return throwable -> {
            String throwablesStr = t.toString();
            if (throwablesStr.contains("GaiException") ||
                    throwablesStr.contains("SocketTimeoutException") ||
                    throwablesStr.contains("UnknownHostException") ||
                    throwablesStr.contains("SocketException")) {
                ToastUtil.showShort("网络问题,加载失败");
            }
            Log.e(TAG, t.getMessage());
            ToastUtil.showShort(t.getMessage());
        };
    }

    /**
     * http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1
     *
     * @param idx    跟最新图片日期相差的天数
     * @param number 请求图片的数量
     */
    public Observable<YingPic> fetchYingPic(int idx, int number) {
        return yingApi.getimages(Constant.YING_FORMAT, idx, number)
                .map(yingPicResult -> yingPicResult.images.get(0))
                .compose(RxUtil.io());
    }


//    public static YingApi getYingApi() {
//        if (yingApi == null) {
//            Retrofit retrofit = new Retrofit.Builder()
//                    .client(okHttpClient)
//                    .baseUrl(YingApi.HOST)
//                    .addConverterFactory(gsonConverterFactory)
//                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
//                    .build();
//            yingApi = retrofit.create(YingApi.class);
//        }
//        return yingApi;
//    }

}
