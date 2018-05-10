package com.yueyue.glidedemo.base;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yueyue.glidedemo.BuildConfig;
import com.yueyue.glidedemo.R;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * BaseFragment helps onCreateView, and initViews(when root is null), init data on Activity Created.
 */
public abstract class BaseFragment extends Fragment {

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    protected View rootView;
    protected Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(initLayoutId(), container, false);
            ButterKnife.bind(this, rootView);
            initViews();
        }
        onCreateView();
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isHidden", isHidden());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }


    protected void unsubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected abstract int initLayoutId();

    protected void onCreateView() {
        FragmentActivity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            toolbar = ((BaseActivity) activity).toolbar;
        }
    }

    protected void showDialog(int layoutId, int titleId) {
        FragmentActivity activity = getActivity();
        if (activity == null) return;

        View view = activity.getLayoutInflater().inflate(layoutId, null);
        new AlertDialog.Builder(activity)
                .setTitle(titleId)
                .setView(view)
                .setPositiveButton(R.string.sure, null)
                .show();
    }

    protected abstract void initViews();

    protected abstract void initData();

    //RxJava之过滤操作符 - 行云间 - CSDN博客
    //      http://blog.csdn.net/io_field/article/details/51378909
//    public <T> Observable.Transformer<T, T> applySchedulers() {
//        return observable -> observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .distinct();
//    }


    public void log(String key, String content) {
        if (BuildConfig.DEBUG && getUserVisibleHint()) {
            Log.d(getClass().getSimpleName(), key + "  " + content);
        }
    }

    public void log(String key, int content) {
        log(key, String.valueOf(content));
    }

    public void log(String key) {
        log(key, "");
    }

}
