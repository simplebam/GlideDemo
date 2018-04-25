package com.yueyue.glidedemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.yueyue.glidedemo.R;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * author : yueyue on 2017/12/25 19:09
 * desc   : BaseActivity includes a base layoutId, init its toolbar (if the layout has one)
 * quote  : http://blog.csdn.net/MeloDev/article/details/53406019
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public Toolbar toolbar;
    private boolean isShowToolbar = true;

    protected abstract int initLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews(savedInstanceState);
    }

    /**
     * MUST override and call the SUPER method
     */
    protected void initViews(@Nullable Bundle savedInstanceState) {
        setContentView(initLayoutId());
        ButterKnife.bind(this);
        initAppBar();
    }



    public void initAppBar() {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            //设置显示返回箭头
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    public void setToolbarTitle(String title) {
        if (toolbar != null) toolbar.setTitle(title);
    }

    public void toggleToolbar() {
        if (isShowToolbar) {
            hideToolbar();
        } else {
            showToolbar();
        }
    }

    /**
     * 自定义控件三部曲之动画篇（二）——Interpolator插值器 - CSDN博客:
     * http://blog.csdn.net/harvic880925/article/details/40049763
     */
    public void hideToolbar() {
        if (toolbar != null) {
            isShowToolbar = false;
            toolbar.animate()
                    .translationY(-toolbar.getBottom())
                    .setInterpolator(new AccelerateInterpolator())
                    .start();
        }
    }

    /**
     * 自定义控件三部曲之动画篇（二）——Interpolator插值器 - CSDN博客:
     * http://blog.csdn.net/harvic880925/article/details/40049763
     */
    public void showToolbar() {
        if (toolbar != null) {
            isShowToolbar = true;
            toolbar.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator())
                    .start();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
