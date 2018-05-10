package com.yueyue.glidedemo.module.chapter_3;

import com.bumptech.glide.load.model.GlideUrl;

/**
 * author : yueyue on 2018/5/10 16:32
 * desc   : http://p8hz8s7pk.bkt.clouddn.com/qiniu_test.png?e=1525939113834&token=MY_ACCESS_KEY:v4x9pmncDMQ0JNFVOhvs
 * 其实七牛云仅仅http://p8hz8s7pk.bkt.clouddn.com/qiniu_test.png都可以获取到图片,因为我没有设置严格的Token权限,
 * 为了Glide仅对qiniu_test.png保存一份,需要去除e=1525939113834&token=MY_ACCESS_KEY:v4x9pmncDMQ0JNFVOhvs
 */
public class MyGlideUrl extends GlideUrl {

    private String mUrl;

    public MyGlideUrl(String url) {
        super(url);
        mUrl = url;
    }

    @Override
    public String getCacheKey() {
        return mUrl
                .replace(findTokenParam(), "")
                .replace(findTimestampParam(), "");
    }

    private String findTokenParam() {
        //http://p8hz8s7pk.bkt.clouddn.com/qiniu_test.png?token=MY_ACCESS_KEY:v4x9pmncDMQ0JNFVOhvs&e=1525939113834
        String tokenParam = "";
        int tokenKeyIndex = mUrl.indexOf("?token=") >= 0 ? mUrl.indexOf("?token=") : mUrl.indexOf("&token=");
        if (tokenKeyIndex != -1) {
            int nextAndIndex = mUrl.indexOf("&", tokenKeyIndex + 1);
            if (nextAndIndex != -1) {
                tokenParam = mUrl.substring(tokenKeyIndex + 1, nextAndIndex + 1);
            } else {
                tokenParam = mUrl.substring(tokenKeyIndex);
            }
        }
        return tokenParam;
    }

    private String findTimestampParam() {
        //http://p8hz8s7pk.bkt.clouddn.com/qiniu_test.png?token=MY_ACCESS_KEY:v4x9pmncDMQ0JNFVOhvs&e=1525939113834
        String tokenParam = "";
        int tokenKeyIndex = mUrl.indexOf("?e=") >= 0 ? mUrl.indexOf("?e=") : mUrl.indexOf("&e=");
        if (tokenKeyIndex != -1) {
            int nextAndIndex = mUrl.indexOf("&", tokenKeyIndex + 1);
            if (nextAndIndex != -1) {
                tokenParam = mUrl.substring(tokenKeyIndex + 1, nextAndIndex + 1);
            } else {
                tokenParam = mUrl.substring(tokenKeyIndex);
            }
        }
        return tokenParam;
    }

}