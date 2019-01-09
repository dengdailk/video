package com.video.lk.video.Http;

import com.video.lk.video.Bean.TodayBean;
import com.video.lk.video.Bean.VideoBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * File description
 *
 * @author lk
 * @date 2019/1/8 09 31
 */
public interface RetrofitService {


    /*
     *http://is.snssdk.com/api/news/feed/v51/?category=video
     */
    @GET("news/feed/v51")
    Observable<TodayBean> getToday(@Query("category") String category);
    /*
     *http://ib.365yg.com/video/urls/v/1/toutiao/mp4/v02004f00000bbpbk3l2v325q7lmkds0?r=6781281688452415&s=2734808831
     */
    @GET
    Observable<VideoBean> getVideo(@Url String url);
}
