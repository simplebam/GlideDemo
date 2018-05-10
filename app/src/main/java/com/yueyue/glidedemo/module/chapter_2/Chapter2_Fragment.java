package com.yueyue.glidedemo.module.chapter_2;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.widget.Button;

import com.yueyue.glidedemo.R;
import com.yueyue.glidedemo.base.BaseFragment;
import com.yueyue.glidedemo.utils.ConvertUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : yueyue on 2018/4/21 20:53
 * desc   : Android图片加载框架最全解析（一），Glide的基本用法 - CSDN博客
 * <p>https://blog.csdn.net/guolin_blog/article/details/53759439</p>
 */

/**
 * Glide.with(this).load(url).into(imageView);
 * with: 如果我们是在非主线程当中使用的Glide，那么不管你是传入的Activity还是Fragment，都会被强制当成Application来处理。
 * 所以我们推荐再主线程使用Glide
 */
public class Chapter2_Fragment extends BaseFragment {

    private static final String TAG = Chapter2_Fragment.class.getSimpleName();

    @BindView(R.id.btn_tip)
    Button mBtnTip;
    @BindView(R.id.source_tip)
    Button mBtnSourceTip;

    @OnClick(R.id.btn_tip)
    void showTips() {
        showDialog(R.layout.dialog_tip_chapter2, R.string.title_complete);
    }

    @OnClick(R.id.source_tip)
    void showSourceTips() {
        showDialog(R.layout.dialog_source_chapter2, R.string.how_to_read_source);
    }


    @Override
    protected int initLayoutId() {
        return R.layout.fragment_chapter2;
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
            mBtnTip.setBackgroundDrawable(gd);
            mBtnSourceTip.setBackgroundDrawable(gd);
        } else {
            mBtnTip.setBackground(gd);
            mBtnSourceTip.setBackground(gd);
        }
    }

    @Override
    protected void initData() {
    }


    public static Chapter2_Fragment launch() {
        return new Chapter2_Fragment();
    }
}
