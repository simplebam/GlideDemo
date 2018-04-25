package com.yueyue.glidedemo.utils;

import android.arch.lifecycle.Lifecycle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.trello.lifecycle2.android.lifecycle.AndroidLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HugoXie on 16/5/19.
 * <p>
 * Email: Hugo3641@gamil.com
 * GitHub: https://github.com/xcc3641
 * Info: 封装 Rx 的一些方法
 */
public class RxUtil {

    private static <T> ObservableTransformer<T, T> schedulerTransformer(Scheduler scheduler) {
        return observable ->
                observable
                        .subscribeOn(scheduler)
                        .observeOn(AndroidSchedulers.mainThread(), true);
    }

    public static <T> ObservableTransformer<T, T> io() {
        return schedulerTransformer(Schedulers.io());
    }

    private static <T> FlowableTransformer<T, T> schedulerTransformerF(Scheduler scheduler) {
        return flowable ->
                flowable
                        .subscribeOn(scheduler)
                        .observeOn(AndroidSchedulers.mainThread(), true);
    }

    public static <T> FlowableTransformer<T, T> ioF() {
        return schedulerTransformerF(Schedulers.io());
    }

    public static <T> ObservableTransformer<T, T> activityLifecycle(AppCompatActivity activity) {
        LifecycleProvider<Lifecycle.Event> lifecycleProvider = AndroidLifecycle.createLifecycleProvider(activity);
        return observable ->
                observable.compose(lifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_DESTROY));
    }

    public static <T> ObservableTransformer<T, T> fragmentLifecycle(Fragment fragment) {
        LifecycleProvider<Lifecycle.Event> lifecycleProvider = AndroidLifecycle.createLifecycleProvider(fragment);
        return observable ->
                observable.compose(lifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_DESTROY));
    }

    public static <T> FlowableTransformer<T, T> activityLifecycleF(AppCompatActivity activity) {
        LifecycleProvider<Lifecycle.Event> lifecycleProvider = AndroidLifecycle.createLifecycleProvider(activity);
        return flowable ->
                flowable.compose(lifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_DESTROY));
    }

    public static <T> FlowableTransformer<T, T> fragmentLifecycleF(Fragment fragment) {
        LifecycleProvider<Lifecycle.Event> lifecycleProvider = AndroidLifecycle.createLifecycleProvider(fragment);
        return flowable ->
                flowable.compose(lifecycleProvider.bindUntilEvent(Lifecycle.Event.ON_DESTROY));
    }

}