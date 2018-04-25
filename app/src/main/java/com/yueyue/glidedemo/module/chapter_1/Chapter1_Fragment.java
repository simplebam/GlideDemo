package com.yueyue.glidedemo.module.chapter_1;

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
 * desc   :
 */
public class Chapter1_Fragment extends BaseFragment {

    private static final String TAG = Chapter1_Fragment.class.getSimpleName();

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.iv_image)
    ImageView mIvImage;
    @BindView(R.id.btn_request)
    Button mBtnRequest;

    @OnClick(R.id.btn_request)
    void load() {
        unsubscribe();
        changeSwipeRefreshState(true);

        int random = new Random().nextInt(10) + 1;
        Disposable disposable = NetworkService.getInstance()
                .fetchYingPic(random, 1)
                .subscribe(yingPic -> {
                            Context context = getContext() == null ? App.getContext() : getContext();
                            RequestOptions options = RequestOptions
                                    .placeholderOf(R.drawable.placeholder)
                                    .error(R.drawable.ying_default);
                            Glide.with(context).load(YingApi.HOST + yingPic.url).apply(options).into(mIvImage);

                            changeSwipeRefreshState(false);
                        },
                        throwable -> {
                            changeSwipeRefreshState(false);
                            Log.e(TAG, "load: " + throwable.toString());
                            ToastUtil.showShort(R.string.load_error);
                        });
        mCompositeDisposable.add(disposable);

    }


    private void changeSwipeRefreshState(boolean swipeRefresh) {
        mSwipeRefresh.setRefreshing(swipeRefresh);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_chapter1;
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
        } else {
            mBtnRequest.setBackground(gd);
        }
    }

    @Override
    protected void initData() {
        load();
    }


    public static Chapter1_Fragment launch() {
        return new Chapter1_Fragment();
    }
}
