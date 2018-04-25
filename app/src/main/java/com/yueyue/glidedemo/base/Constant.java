package com.yueyue.glidedemo.base;

import com.yueyue.glidedemo.utils.FileUtil;

/**
 * author : yueyue on 2018/4/18 10:44
 * desc   :
 */
public interface Constant {

    // 网络数据缓存地址
    String NET_CACHE = FileUtil.getDiskCacheDir(App.getContext(), "NetCache").getParent();

    //http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1
    String YING_FORMAT="js";
}
