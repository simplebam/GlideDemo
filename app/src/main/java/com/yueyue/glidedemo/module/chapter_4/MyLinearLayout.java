package com.yueyue.glidedemo.module.chapter_4;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * author : yueyue on 2018/5/10 20:10
 * desc   :
 */
public class MyLinearLayout extends LinearLayout {

    private ViewTarget<MyLinearLayout, Drawable> mViewtarget;

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewTarget();
    }

    private void initViewTarget() {
        mViewtarget = new ViewTarget<MyLinearLayout, Drawable>(this) {
            @Override
            public void onResourceReady(@NonNull Drawable resource,
                                        @Nullable Transition<? super Drawable> transition) {
                MyLinearLayout myLayout = getView();
                myLayout.setImageAsBackground(resource);
            }
        };
    }

    public ViewTarget<MyLinearLayout, Drawable> getTarget() {
        return mViewtarget;
    }

    public void setImageAsBackground(Drawable resource) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(resource);
        } else {
            setBackgroundDrawable(resource);
        }
    }

}
