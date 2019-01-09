package com.video.lk.video.Http;

import com.video.lk.video.Bean.TodayBean;
import com.video.lk.video.Bean.VideoBean;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * File description
 *
 * @author lk
 * @date 2019/1/8 09 31
 */
public class RetrofitHelper {
    private static OkHttpClient okHttpClient;
    private RetrofitService retrofitService;

    public RetrofitHelper(String host) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public Observable<TodayBean> getToday(String category){
        return retrofitService.getToday(category);
    }
    public Observable<VideoBean> getVideoUrl(String api){
        return retrofitService.getVideo(api);
    }


    public OkHttpClient getOkHttpClient() {
        if (okHttpClient==null){
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();

        }
        return okHttpClient;
    }
}
