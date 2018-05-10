package com.yueyue.glidedemo.module.chapter_4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
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
 * desc   : Android图片加载框架最全解析（一），Glide的基本用法 - CSDN博客
 * <p>https://blog.csdn.net/guolin_blog/article/details/53759439</p>
 */
public class Chapter4_Fragment extends BaseFragment {

    private static final String TAG = Chapter4_Fragment.class.getSimpleName();

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.iv_image)
    ImageView mIvImage;
    @BindView(R.id.btn_simple_target)
    Button mBtnSimpleTarget;
    @BindView(R.id.btn_view_target)
    Button mBtnViewTarget;

    @OnClick(R.id.btn_simple_target)
    void loadSimple() {
        unsubscribe();
        changeSwipeRefreshState(true);

        int random = new Random().nextInt(10) + 1;
        Disposable disposable = NetworkService.getInstance()
                .fetchYingPic(random, 1)
                .subscribe(yingPic -> {
                            String picUrl = YingApi.HOST + yingPic.url;
                            loadImage(picUrl);

                            changeSwipeRefreshState(false);
                        },
                        throwable -> {
                            changeSwipeRefreshState(false);
                            ToastUtil.showShort(R.string.load_error);
                            Log.e(TAG, "load: " + throwable.toString());
                        });
        mCompositeDisposable.add(disposable);

    }

    private void loadImage(String picUrl) {
        Context context = getContext() == null ? App.getContext() : getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error);
        //Glide4版本之后的asBitmap()必须要在load()之前,之前版本都是asBitmap()在load()之后
        Glide.with(context)
                .asBitmap()
                .load(picUrl)
                .apply(options)
                .into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                //当使用SimpleTarget<Bitmap>的泛型为Bitmap,需要指定加载图片的加载类型为Bitmap,即调用asBitmap()
                mIvImage.setImageBitmap(resource);
                String DrawableClazzName = resource.getClass().getSimpleName();
                ToastUtil.showShort(getString(R.string.load_picture_and_data, DrawableClazzName, resource.getByteCount()));
            }
        });
    }


    private void changeSwipeRefreshState(boolean swipeRefresh) {
        mSwipeRefresh.setRefreshing(swipeRefresh);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_chapter4;
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
            mBtnSimpleTarget.setBackgroundDrawable(gd);
            mBtnViewTarget.setBackgroundDrawable(gd);
        } else {
            mBtnSimpleTarget.setBackground(gd);
            mBtnViewTarget.setBackground(gd);
        }
    }

    @Override
    protected void initData() {
        loadSimple();
    }


    public static Chapter4_Fragment launch() {
        return new Chapter4_Fragment();
    }
}
