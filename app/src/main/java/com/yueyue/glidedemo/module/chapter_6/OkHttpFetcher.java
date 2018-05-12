package com.yueyue.glidedemo.module.chapter_6;

import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;
import com.bumptech.glide.util.LogTime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author : yueyue on 2018/5/12 11:13
 * desc   : 这里仿照 {@link com.bumptech.glide.load.data.HttpUrlFetcher}
 *          Android图片加载框架最全解析（六），探究Glide的自定义模块功能 - CSDN博客 https://blog.csdn.net/guolin_blog/article/details/78179422
 */
public class OkHttpFetcher implements DataFetcher<InputStream> {

    private static final String TAG = OkHttpFetcher.class.getSimpleName();

    private final OkHttpClient client;
    private final GlideUrl glideUrl;
    private InputStream stream;
    private ResponseBody responseBody;
    private volatile boolean isCancelled;

    public OkHttpFetcher(OkHttpClient client, GlideUrl glideUrl) {
        this.client = client;
        this.glideUrl = glideUrl;
    }

    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataCallback<? super InputStream> callback) {
        long startTime = LogTime.getLogTime();
        try {
            InputStream result = loadDataWithRedirects(glideUrl.toURL(), glideUrl.getHeaders());
            callback.onDataReady(result);
        } catch (IOException e) {
            if (Log.isLoggable(TAG, Log.DEBUG)) {
                Log.d(TAG, "Failed to load data for glideUrl", e);
            }
            callback.onLoadFailed(e);
        } finally {
            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                Log.v(TAG, "Finished http glideUrl fetcher fetch in " + LogTime.getElapsedMillis(startTime));
            }
        }


    }

    private InputStream loadDataWithRedirects(URL url, Map<String, String> headers) throws IOException {

        //添加头信息
        Request.Builder requestBuilder = new Request.Builder().url(url);
        for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
            String key = headerEntry.getKey();
            requestBuilder.addHeader(key, headerEntry.getValue());
        }

        //添加下面这个是待会儿我们用来进行测试验证的，大家实际项目中的代码无须添加这个请求头。
        requestBuilder.addHeader("httplib", "OkHttp");
        //使用小米4c安装应用,fidder4抓包,然后抓取到的
     /* GET //az/hprichbg/rb/HollowRock_ZH-CN11829527473_1920x1080.jpg HTTP/1.1
        User-Agent: Dalvik/2.1.0 (Linux; U; Android 7.0; Mi-4c MIUI/8.4.26)
        httplib: OkHttp
        Host: cn.bing.com
        Connection: Keep-Alive
        Accept-Encoding: gzip*/

        Request request = requestBuilder.build();

        if (isCancelled) {
            return null;
        }

        //开始请求
        Response response = client.newCall(request).execute();
        responseBody = response.body();
        if (!response.isSuccessful() || responseBody == null) {
            throw new IOException("Request failed with code: " + response.code());
        }

        stream = ContentLengthInputStream.obtain(responseBody.byteStream(),
                responseBody.contentLength());
        return stream;
    }

    @Override
    public void cleanup() {
        try {
            if (stream != null) {
                stream.close();
            }
            if (responseBody != null) {
                responseBody.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancel() {
        isCancelled = true;
    }

    @NonNull
    @Override
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return DataSource.REMOTE;
    }
}
