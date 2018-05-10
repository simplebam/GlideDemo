package com.yueyue.glidedemo.module.chapter_4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.yueyue.glidedemo.R;
import com.yueyue.glidedemo.base.App;
import com.yueyue.glidedemo.base.BaseFragment;
import com.yueyue.glidedemo.network.NetworkService;
import com.yueyue.glidedemo.network.api.YingApi;
import com.yueyue.glidedemo.utils.ConvertUtil;
import com.yueyue.glidedemo.utils.ToastUtil;

import java.io.File;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    @BindView(R.id.btn_download_only)
    Button mBtnDownloadOnly;
    @BindView(R.id.btn_listener)
    Button mBtnListener;

    @BindView(R.id.ll_linear_layout)
    MyLinearLayout mMyLinearLayout;

    @OnClick(R.id.btn_simple_target)
    void load2Simple() {
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
                            //Glide4版本之后的asBitmap()必须要在load()之前,之前版本都是asBitmap()在load()之后
                            Glide.with(context).asBitmap().load(YingApi.HOST + yingPic.url).apply(options)
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                            //当使用SimpleTarget<Bitmap>的泛型为Bitmap,需要指定加载图片的加载类型为Bitmap,即调用asBitmap()
                                            mIvImage.setImageBitmap(resource);
                                            String DrawableClazzName = resource.getClass().getSimpleName();
                                            ToastUtil.showShort(TAG + getString(R.string.load_picture_and_data, DrawableClazzName, resource.getByteCount()));
                                        }
                                    });

                            changeSwipeRefreshState(false);
                        },
                        throwable -> {
                            changeSwipeRefreshState(false);
                            ToastUtil.showShort(R.string.load_error);
                            Log.e(TAG, "load: " + throwable.toString());
                        });
        mCompositeDisposable.add(disposable);

    }

    @OnClick(R.id.btn_view_target)
    void load2View() {
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
                            Glide.with(context)
                                    .load(YingApi.HOST + yingPic.url)
                                    .apply(options)
                                    .into(mMyLinearLayout.getTarget());
                            ToastUtil.showShort(TAG + "中load2View()方法加载成功");
                            changeSwipeRefreshState(false);
                        },
                        throwable -> {
                            changeSwipeRefreshState(false);
                            ToastUtil.showShort(R.string.load_error);
                            Log.e(TAG, "load: " + throwable.toString());
                        });
        mCompositeDisposable.add(disposable);
    }

    @OnClick(R.id.btn_download_only)
    void downloadImageOnly() {
        unsubscribe();
        changeSwipeRefreshState(true);

        Disposable disposable = NetworkService.getInstance()
                .fetchYingPic(1, 1)
                .observeOn(Schedulers.newThread())   //需要使用新线程
                .map(yingPic -> {

                    RequestOptions options = new RequestOptions()
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.error);

                    //Glide4的submit代替了Glide3的downloadOnly方法,
                    // 其中downloadOnly(int width, int height)是用于在子线程中下载图片的，
                    // 而downloadOnly(Y target)是用于在主线程中下载图片的
                    // 但submit()的两个方法都是在子线程运行的,因为涉及阻塞
                    FutureTarget<File> futureTarget = Glide.with(App.getContext())
                            .asFile()
                            .load(YingApi.HOST + yingPic.url)
                            .apply(options)
                            .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
                    return futureTarget.get();
                })
                .observeOn(AndroidSchedulers.mainThread())   //切换回主线程弹toast以及操作UI
                .subscribe(imageFile -> {
                            ToastUtil.showShort(TAG + "的downloadImageOnly的imageFile:" + imageFile.getPath());

                            changeSwipeRefreshState(false);
                        },
                        throwable -> {
                            changeSwipeRefreshState(false);
                            ToastUtil.showShort(R.string.load_error);
                            Log.e(TAG, "load: " + throwable.toString());
                        });
        mCompositeDisposable.add(disposable);
    }

    @OnClick(R.id.btn_listener)
    void load2listener() {
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
                            Glide.with(context)
                                    .load(YingApi.HOST + yingPic.url)
                                    .apply(options)
                                    .listener(new RequestListener<Drawable>() {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e,
                                                                    Object model,
                                                                    Target<Drawable> target,
                                                                    boolean isFirstResource) {
                                            //返回false就表示这个事件没有被处理，还会继续向下传递，
                                            //返回true就表示这个事件已经被处理掉了,即不调用setErrorPlaceholder(这个方法里面调用target.onLoadFailed方法);
//                                            if ((requestListener == null
//                                                    || !requestListener.onLoadFailed(e, model, target, isFirstReadyResource()))
//                                                    && (targetListener == null
//                                                    || !targetListener.onLoadFailed(e, model, target, isFirstReadyResource()))) {
//                                                setErrorPlaceholder();
//                                            }
                                            //Glide已经把以上的源码放去SingleRequest这个类了,不再是GenericRequest
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(Drawable resource,
                                                                       Object model,
                                                                       Target<Drawable> target,
                                                                       DataSource dataSource,
                                                                       boolean isFirstResource) {
                                            ToastUtil.showShort("listener接口的onResourceReady走了");
                                            //返回false就表示这个事件没有被处理，还会继续向下传递,
                                            //返回true就表示这个事件已经被处理掉了,即不调用 target.onResourceReady();
                                            return false;
                                        }
                                    })
                                    .into(mIvImage);

                            ToastUtil.showShort(TAG + "中load2listener()方法加载成功");
                            changeSwipeRefreshState(false);
                        },
                        throwable -> {
                            changeSwipeRefreshState(false);
                            ToastUtil.showShort(R.string.load_error);
                            Log.e(TAG, "load: " + throwable.toString());
                        });
        mCompositeDisposable.add(disposable);
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
            mBtnDownloadOnly.setBackgroundDrawable(gd);
        } else {
            mBtnSimpleTarget.setBackground(gd);
            mBtnViewTarget.setBackground(gd);
            mBtnDownloadOnly.setBackground(gd);
        }
    }

    @Override
    protected void initData() {
        load2Simple();
    }


    public static Chapter4_Fragment launch() {
        return new Chapter4_Fragment();
    }
}
