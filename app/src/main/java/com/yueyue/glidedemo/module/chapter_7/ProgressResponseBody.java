package com.yueyue.glidedemo.module.chapter_7;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * author : yueyue on 2018/5/12 19:15
 * desc   :
 */
public class ProgressResponseBody extends ResponseBody {

    private static final String TAG = ProgressResponseBody.class.getSimpleName();

    private BufferedSource bufferedSource;

    private ResponseBody responseBody;

    private ProgressListener listener;

    public ProgressResponseBody(String url, ResponseBody responseBody) {
        this.responseBody = responseBody;
        this.listener = ProgressInterceptor.LISTENER_MAP.get(url);
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            synchronized (BufferedSource.class) {
                bufferedSource = Okio.buffer(new ProgressSource(responseBody.source()));
            }
        }
        return bufferedSource;
    }

    /**
     * ForwardingSource也是一个使用委托模式的工具，它不处理任何具体的逻辑，只是负责将传入的原始Source对象进行中转。
     * 但是，我们使用ProgressSource继承自ForwardingSource，那么就可以在中转的过程中加入自己的逻辑了。
     */
    private class ProgressSource extends ForwardingSource {

        long totalBytesRead = 0L;

        int currentProgress = 0;

        public ProgressSource(Source delegate) {
            super(delegate);
        }


        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            long bytesRead = super.read(sink, byteCount); //该次中转的字节长度,即该次读取的字节长度
            long fullLength = responseBody.contentLength();

            if (bytesRead == -1) {
                //已经读完了,为了后面的计算,让totalBytesRead = fullLength
                totalBytesRead = fullLength;
            } else {
                totalBytesRead += bytesRead;
            }

            int progress = (int) (100f * totalBytesRead / fullLength);
            Log.i(TAG, "download progress is " + progress);

            if (listener != null && progress != currentProgress) {
                listener.onProgress(progress);
            }

            if (listener != null && totalBytesRead == fullLength) {
                //读完了
                listener = null;
            }

            currentProgress = progress;
            return bytesRead;
        }
    }
}
