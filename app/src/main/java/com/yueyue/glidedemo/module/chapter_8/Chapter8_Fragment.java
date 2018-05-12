package com.yueyue.glidedemo.module.chapter_8;

import com.yueyue.glidedemo.R;
import com.yueyue.glidedemo.base.BaseFragment;

/**
 * author : yueyue on 2018/4/21 20:53
 * desc   : Android图片加载框架最全解析（八），带你全面了解Glide 4的用法 - CSDN博客
 * <p>  https://blog.csdn.net/guolin_blog/article/details/78582548</p>
 */
public class Chapter8_Fragment extends BaseFragment {

    private static final String TAG = Chapter8_Fragment.class.getSimpleName();

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_chapter8;
    }

    @Override
    protected void initViews() {
    }


    @Override
    protected void initData() {

    }

    public static Chapter8_Fragment launch() {
        return new Chapter8_Fragment();
    }
}
