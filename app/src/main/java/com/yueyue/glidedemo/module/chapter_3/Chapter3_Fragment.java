package com.yueyue.glidedemo.module.chapter_3;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestOptions;
import com.yueyue.glidedemo.R;
import com.yueyue.glidedemo.base.App;
import com.yueyue.glidedemo.base.BaseFragment;
import com.yueyue.glidedemo.utils.ConvertUtil;
import com.yueyue.glidedemo.utils.EncryptUtil;
import com.yueyue.glidedemo.utils.FileUtil;
import com.yueyue.glidedemo.utils.ToastUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author : yueyue on 2018/4/21 20:53
 * desc   :Android图片加载框架最全解析（三），深入探究Glide的缓存机制 - CSDN博客
 * <p>https://blog.csdn.net/guolin_blog/article/details/54895665</p>
 */
public class Chapter3_Fragment extends BaseFragment {

    private static final String TAG = Chapter3_Fragment.class.getSimpleName();

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.iv_image)
    ImageView mIvImage;
    @BindView(R.id.btn_request)
    Button mBtnRequest;
    @BindView(R.id.btn_glide_file_size)
    Button mBtnGlideFileSize;

    @OnClick(R.id.btn_request)
    void load() {
        unsubscribe();
        changeSwipeRefreshState(true);

        Disposable disposable = Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(aLong -> getQiNiuImageUrl())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(url -> {
                            Context context = getContext() == null ? App.getContext() : getContext();
                            RequestOptions options = new RequestOptions()
                                    .placeholder(R.drawable.placeholder)
                                    .error(R.drawable.error);

                            GlideUrl glideUrl = new MyGlideUrl(url);
                            Glide.with(context).load(glideUrl).apply(options).into(mIvImage);

                            getGlideFileSize();

                            changeSwipeRefreshState(false);
                        },
                        throwable -> {
                            changeSwipeRefreshState(false);
                            ToastUtil.showShort(R.string.load_error);
                            Log.e(TAG, "load: " + throwable.toString());
                        });

        mCompositeDisposable.add(disposable);
    }

    @NonNull
    private String getQiNiuImageUrl() {
        //下载凭证 - 七牛开发者中心 https://developer.qiniu.com/kodo/manual/1202/download-token
        String baseUrl = "http://p8hz8s7pk.bkt.clouddn.com/qiniu_test.png";

        //JAVA获取时间戳，哪个更快 - 潇湘客 - ITeye博客 http://tangmingjie2009.iteye.com/blog/1543166
        long e = System.currentTimeMillis();
        String baseDownloadUrl = baseUrl + "?e=" + e;

        //第二个参数encryptKey需要自己去注册的,为七牛云的SecretKey/sk
        byte[] sign = EncryptUtil.HmacSHA1(baseDownloadUrl, "v-vHuAmzFdphjw9mPhELncUYe_OJbQnzLZroEMWC");
        String token = "MY_ACCESS_KEY:" + EncryptUtil.safeUrlBase64Encode(sign);

        return baseDownloadUrl + "&token=" + token;
    }

    @OnClick(R.id.btn_glide_file_size)
    void getGlideFileSize() {
        String size = null;
        try {
            Context context = getContext() == null ? App.getContext() : getContext();
            size = FileUtil.getFormatSize(FileUtil.getFolderSize(
                    new File(context.getCacheDir()
                            + "/" + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR)));

        } catch (Exception e) {
            size = "0";
            e.printStackTrace();
        }

        ToastUtil.showShort("Chapter3_Fragment当前Glide缓存大小:" + size);
    }


    @OnClick(R.id.btn_tip)
    void showTips() {
        showDialog(R.layout.dialog_tip_chapter3, R.string.tip);
    }

    private void changeSwipeRefreshState(boolean swipeRefresh) {
        mSwipeRefresh.setRefreshing(swipeRefresh);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_chapter3;
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
            mBtnRequest.setBackgroundDrawable(gd);
            mBtnGlideFileSize.setBackgroundDrawable(gd);
        } else {
            mBtnRequest.setBackground(gd);
            mBtnGlideFileSize.setBackground(gd);
        }
    }

    @Override
    protected void initData() {
        load();
    }


    public static Chapter3_Fragment launch() {
        return new Chapter3_Fragment();
    }
}
