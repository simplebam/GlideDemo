package com.yueyue.glidedemo.module.chapter_3;

import com.bumptech.glide.load.model.GlideUrl;

/**
 * author : yueyue on 2018/5/10 16:32
 * desc   : http://p8hz8s7pk.bkt.clouddn.com/qiniu_test.png?e=1525939113834&token=MY_ACCESS_KEY:v4x9pmncDMQ0JNFVOhvs
 * 其实七牛云仅仅http://p8hz8s7pk.bkt.clouddn.com/qiniu_test.png都可以获取到图片,因为我没有设置严格的Token权限,
 * 为了Glide仅对qiniu_test.png保存一份,需要去除e=1525939113834&token=MY_ACCESS_KEY:v4x9pmncDMQ0JNFVOhvs
 */
public class MyGlideUrl extends GlideUrl {

    private static final String TAG = MyGlideUrl.class.getSimpleName();

    private String mUrl;

    public MyGlideUrl(String url) {
        super(url);
        mUrl = url;
    }

    @Override
    public String getCacheKey() {
        String replacedTokenStr = mUrl.replace(findTokenParam(mUrl), "");
        return replacedTokenStr.replace(findTimestampParam(replacedTokenStr), "");
    }

    private String findTokenParam(String matchStr) {
        //http://p8hz8s7pk.bkt.clouddn.com/qiniu_test.png?token=MY_ACCESS_KEY:v4x9pmncDMQ0JNFVOhvs&e=1525939113834
        String tokenParam = "";
        int tokenKeyIndex = matchStr.contains("?token=") ? matchStr.indexOf("?token=") : matchStr.indexOf("&token=");
        if (tokenKeyIndex != -1) {
            int nextAndIndex = matchStr.indexOf("&", tokenKeyIndex + 1);
            if (nextAndIndex != -1) {
                tokenParam = matchStr.substring(tokenKeyIndex + 1, nextAndIndex + 1);
            } else {
                tokenParam = matchStr.substring(tokenKeyIndex);
            }
        }
        return tokenParam;
    }

    private String findTimestampParam(String matchStr) {
        //http://p8hz8s7pk.bkt.clouddn.com/qiniu_test.png?e=1525939113834
        String timestampParam = "";
        int timestampKeyIndex = matchStr.contains("?e=") ? matchStr.indexOf("?e=") : matchStr.indexOf("&e=");
        if (timestampKeyIndex != -1) {
            int nextAndIndex = matchStr.indexOf("&", timestampKeyIndex + 1);
            if (nextAndIndex != -1) {
                timestampParam = matchStr.substring(timestampKeyIndex + 1, nextAndIndex + 1);
            } else {
                timestampParam = matchStr.substring(timestampKeyIndex);
            }
        }
        return timestampParam;
    }

}