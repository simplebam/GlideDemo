package com.yueyue.glidedemo.module.chapter_6;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yueyue.glidedemo.R;
import com.yueyue.glidedemo.base.App;
import com.yueyue.glidedemo.base.BaseFragment;
import com.yueyue.glidedemo.network.NetworkService;
import com.yueyue.glidedemo.network.api.YingApi;
import com.yueyue.glidedemo.utils.ConvertUtil;
import com.yueyue.glidedemo.utils.ToastUtil;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * author : yueyue on 2018/4/21 20:53
 * desc   : Android图片加载框架最全解析（六），探究Glide的自定义模块功能 - CSDN博客
 * <p> https://blog.csdn.net/guolin_blog/article/details/78179422</p>
 */
public class Chapter6_Fragment extends BaseFragment {

    private static final String TAG = Chapter6_Fragment.class.getSimpleName();

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.iv_image)
    ImageView mIvImage;
    @BindView(R.id.btn_glide_module)
    Button mBtnGlideModule;

    @OnClick(R.id.btn_glide_module)
    void myGlideModule() {
        unsubscribe();
        changeSwipeRefreshState(true);

        int random = new Random().nextInt(10) + 1;
        Disposable disposable = NetworkService.getInstance()
                .fetchYingPic(random, 1)
                .subscribe(yingPic -> {
                            Context context = getContext() == null ? App.getContext() : getContext();
                            RequestOptions options = new RequestOptions()
                                    .placeholder(R.drawable.placeholder)
                                    .error(R.drawable.error);
                            Glide.with(context).load(YingApi.HOST + yingPic.url).apply(options).into(mIvImage);

                            ToastUtil.showShort(TAG+" myGlideModule加载成功");
                            changeSwipeRefreshState(false);
                        },
                        throwable -> {
                            changeSwipeRefreshState(false);
                            ToastUtil.showShort(TAG+" myGlideModule: " + throwable.toString());
                            Log.e(TAG, TAG+" myGlideModule: " + throwable.toString());
                        });
        mCompositeDisposable.add(disposable);

    }


    private void changeSwipeRefreshState(boolean swipeRefresh) {
        mSwipeRefresh.setRefreshing(swipeRefresh);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_chapter6;
    }

    @Override
    protected void initViews() {
        initButtonBg();
    }

    private void initButtonBg() {
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(ConvertUtil.dp2px(2));
        gd.setColor(getResources().getColor(R.color.color_d6d7d7));

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            mBtnGlideModule.setBackgroundDrawable(gd);
        } else {
            mBtnGlideModule.setBackground(gd);
        }
    }

    @Override
    protected void initData() {
        myGlideModule();
    }


    public static Chapter6_Fragment launch() {
        return new Chapter6_Fragment();
    }
}
