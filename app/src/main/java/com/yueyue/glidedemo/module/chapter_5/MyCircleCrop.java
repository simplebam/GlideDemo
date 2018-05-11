package com.yueyue.glidedemo.module.chapter_5;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author : yueyue on 2018/5/11 00:46
 * desc   : 仿照Glide4的com.bumptech.glide.load.resource.bitmap.CircleCrop写的
 * Glide4.0使用浅解 - 简书 https://www.jianshu.com/p/ab97d6bda8ec
 */
public class MyCircleCrop extends BitmapTransformation {

    private static final int VERSION = 1;
    private static final String ID = "com.yueyue.glidedemo.module.chapter_5.MyCircleCrop." + VERSION;
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private static final Lock BITMAP_DRAWABLE_LOCK = new ReentrantLock();

    /**
     * 使用Glide内置的TransformationUtils.circleCrop(pool, toTransform, outWidth, outHeight);也可以
     * @param pool        这个是Glide中的一个Bitmap缓存池，用于对Bitmap对象进行重用，否则每次图片变换都重新创建Bitmap对象将会非常消耗内存
     * @param toTransform 这个是原始图片的Bitmap对象，我们就是要对它来进行图片变换
     * @param outWidth    代表图片变换后的宽度，其实也就是override()方法中传入的宽
     * @param outHeight   代表图片变换后的高度，其实也就是override()方法中传入的高
     */
    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth,
                               int outHeight) {
        //先算出原图宽度和高度中较小的值，因为对图片进行圆形化变换肯定要以较小的那个值作为直径来进行裁剪
        int diameter = Math.min(toTransform.getWidth(), toTransform.getHeight());

        //从Bitmap缓存池中尝试获取一个Bitmap对象来进行重用，如果没有可重用的Bitmap对象的话就创建一个
        final Bitmap toReuse = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        final Bitmap result;
        if (toReuse != null) {
            result = toReuse;
        } else {
            result = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888);
        }

        //具体进行圆形化变换的部分，这里算出了画布的偏移值，并且根据刚才得到的直径算出半径来进行画圆
        int dx = (toTransform.getWidth() - diameter) / 2;
        int dy = (toTransform.getHeight() - diameter) / 2;

        BITMAP_DRAWABLE_LOCK.lock();
        try {
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP,
                    BitmapShader.TileMode.CLAMP);

            if (dx != 0 || dy != 0) {
                Matrix matrix = new Matrix();
                matrix.setTranslate(-dx, -dy);
                shader.setLocalMatrix(matrix);
            }

            paint.setShader(shader);
            paint.setAntiAlias(true);
            float radius = diameter / 2f;
            canvas.drawCircle(radius, radius, radius, paint);
            clear(canvas);
        } finally {
            BITMAP_DRAWABLE_LOCK.unlock();
        }

        //最后，尝试将复用的Bitmap对象重新放回到缓存池当中，
        if (toReuse != null && !toReuse.equals(toTransform)) {
            pool.put(toReuse);
        }

        //并将圆形化变换后的Bitmap对象进行返回。
        return result;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof MyCircleCrop;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }

    private static void clear(Canvas canvas) {
        canvas.setBitmap(null);
    }
}
