package com.yueyue.glidedemo.module.chapter_7;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author : yueyue on 2018/5/12 18:55
 * desc   :
 */
public class ProgressInterceptor implements Interceptor {
    //Android内存优化(使用SparseArray和ArrayMap代替HashMap) - CSDN博客
    //   https://blog.csdn.net/simplebam/article/details/73467149
     static final HashMap<String, ProgressListener> LISTENER_MAP = new HashMap<>();

    public static void addListener(String url, ProgressListener listener) {
        LISTENER_MAP.put(url, listener);
    }

    public static void removeListener(String url) {
        LISTENER_MAP.remove(url);
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        //可以参考一下   缓存 http://www.jianshu.com/p/93153b34310e 以及查看一下readme推荐的那几篇OkHttp
        Request request = chain.request();
        Response response = chain.proceed(request);
        String url = request.url().toString();
        ResponseBody body = response.body();
        Response newResponse = response.newBuilder().body(new ProgressResponseBody(url, body)).build();
        return newResponse;
    }
}
