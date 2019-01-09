package com.video.lk.video.Video.Model;

import com.video.lk.video.Bean.TodayBean;
import com.video.lk.video.Bean.TodayContentBean;
import com.video.lk.video.Bean.VideoBean;
import com.video.lk.video.Http.Api;
import com.video.lk.video.Http.RetrofitHelper;
import com.video.lk.video.Video.Presenter.VideoPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * File description
 *
 * @author lk
 * @date 2019/1/8 11 26
 */
public class VideoModel implements IVideoModel {
    @Override
    public void loadVideo(String category, final IVideoLoadListener iVideoLoadListener) {
        final List<VideoBean> videoList = new ArrayList<>();
        final List<TodayContentBean> contentBeans = new ArrayList<>();
        final RetrofitHelper retrofitHelper = new RetrofitHelper(Api.TODAY_HOST);

        retrofitHelper.getToday(category)
                .flatMap(new Func1<TodayBean, Observable<VideoBean>>() {
                    @Override
                    public Observable<VideoBean> call(TodayBean todayBean) {
                        return Observable.from(todayBean.getData())
                                .flatMap(new Func1<TodayBean.DataBean, Observable<VideoBean>>() {
                                    @Override
                                    public Observable<VideoBean> call(TodayBean.DataBean dataBean) {
                                        String content = dataBean.getContent();
                                        TodayContentBean contentBean = VideoPresenter.getTodayContentBean(content);
                                        contentBeans.add(contentBean);
                                        String api = VideoPresenter.getVideoContentApi(contentBean.getVideo_id());

                                        return retrofitHelper.getVideoUrl(api);
                                    }
                                });
                    }
                })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Subscriber<VideoBean>() {
            @Override
            public void onCompleted() {
                iVideoLoadListener.videoSuccess(videoList,contentBeans);
            }

            @Override
            public void onError(Throwable e) {
                iVideoLoadListener.fail(e);
            }

            @Override
            public void onNext(VideoBean videoBean) {
                videoList.add(videoBean);
            }
        });
    }
}
