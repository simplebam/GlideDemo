package com.yueyue.glidedemo.module.chapter_5;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yueyue.glidedemo.R;
import com.yueyue.glidedemo.base.App;
import com.yueyue.glidedemo.base.BaseFragment;
import com.yueyue.glidedemo.utils.ConvertUtil;
import com.yueyue.glidedemo.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

/**
 * author : yueyue on 2018/4/21 20:53
 * desc   : Android图片加载框架最全解析（五），Glide强大的图片变换功能 - CSDN博客
 * <p>https://blog.csdn.net/guolin_blog/article/details/71524668</p>
 */
public class Chapter5_Fragment extends BaseFragment {

    private static final String TAG = Chapter5_Fragment.class.getSimpleName();

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.iv_image)
    ImageView mIvImage;
    @BindView(R.id.btn_my_circle_crop)
    Button mBtnMyCircleCrop;
    @BindView(R.id.btn_blur_transformations)
    Button mBtnBlurTransformations;
    @BindView(R.id.btn_grayscale_transformations)
    Button mBtnGrayscaleTransformations;
    @BindView(R.id.btn_combination_transformations)
    Button mBtnCombinationTransformations;

    @OnClick(R.id.btn_my_circle_crop)
    void circleCrop() {
        changeSwipeRefreshState(true);

        Context context = getContext() == null ? App.getContext() : getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .override(ConvertUtil.dp2px(200))
                .transform(new MyCircleCrop());
        Glide.with(context).load(R.drawable.ying_default).apply(options).into(mIvImage);

        ToastUtil.showShort(TAG + "自定义的MyCircleCrop变换成功");
        changeSwipeRefreshState(false);

    }

    @OnClick(R.id.btn_blur_transformations)
    void blurTransformation() {
        changeSwipeRefreshState(true);

        Context context = getContext() == null ? App.getContext() : getContext();

        //模糊化
        RequestOptions options = RequestOptions
                .bitmapTransform(new BlurTransformation(10))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error);
        Glide.with(context)
                .load(R.drawable.ying_default)
                .apply(options)
                .into(mIvImage);

        ToastUtil.showShort(TAG + "模糊化成功");
        changeSwipeRefreshState(false);
    }

    @OnClick(R.id.btn_grayscale_transformations)
    void grayscaleTransformation() {
        changeSwipeRefreshState(true);

        Context context = getContext() == null ? App.getContext() : getContext();

        //黑白化
        RequestOptions options = RequestOptions
                .bitmapTransform(new GrayscaleTransformation())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error);
        Glide.with(context)
                .load(R.drawable.ying_default)
                .apply(options)
                .into(mIvImage);

        ToastUtil.showShort(TAG + "黑白化成功");
        changeSwipeRefreshState(false);
    }

    @OnClick(R.id.btn_combination_transformations)
    void combinationTransformation() {
        changeSwipeRefreshState(true);

        Context context = getContext() == null ? App.getContext() : getContext();

        //单一变换使用.bitmapTransform ,混合变换使用.transforms
        //黑白化
        RequestOptions options =
                new RequestOptions()
                .transforms(new GrayscaleTransformation(),new BlurTransformation(10))
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error);
        Glide.with(context)
                .load(R.drawable.ying_default)
                .apply(options)
                .into(mIvImage);

        ToastUtil.showShort(TAG + "模糊黑白化成功");
        changeSwipeRefreshState(false);
    }



    private void changeSwipeRefreshState(boolean swipeRefresh) {
        mSwipeRefresh.setRefreshing(swipeRefresh);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_chapter5;
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
            mBtnMyCircleCrop.setBackgroundDrawable(gd);
            mBtnBlurTransformations.setBackgroundDrawable(gd);
            mBtnGrayscaleTransformations.setBackgroundDrawable(gd);
            mBtnCombinationTransformations.setBackgroundDrawable(gd);
        } else {
            mBtnMyCircleCrop.setBackground(gd);
            mBtnBlurTransformations.setBackground(gd);
            mBtnGrayscaleTransformations.setBackground(gd);
            mBtnCombinationTransformations.setBackground(gd);
        }
    }

    @Override
    protected void initData() {
        circleCrop();
    }


    public static Chapter5_Fragment launch() {
        return new Chapter5_Fragment();
    }
}
