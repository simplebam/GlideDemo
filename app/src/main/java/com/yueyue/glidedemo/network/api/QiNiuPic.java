package com.yueyue.glidedemo.network.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * author : yueyue on 2018/5/10 16:05
 * desc   :
 */
public interface QiNiuPic {

    //http://p8hz8s7pk.bkt.clouddn.com/qiniu_test.png?e=1525939113834&token=MY_ACCESS_KEY:v4x9pmncDMQ0JNFVOhvs
    //而且在Retrofit 2.0中我们还可以在@Url里面定义完整的URL：这种情况下Base URL会被忽略。
    @GET("http://p8hz8s7pk.bkt.clouddn.com/{file_name}")
    Observable<ResponseBody> getimages(@Path("file_name") String fileName,
                                       @Query("e") long timestamp,
                                       @Query("token") String token);
}
