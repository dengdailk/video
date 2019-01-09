package com.video.lk.video.Video.Model;

import com.video.lk.video.Bean.TodayBean;
import com.video.lk.video.Bean.TodayContentBean;
import com.video.lk.video.Bean.VideoBean;

import java.util.List;

/**
 * File description
 *
 * @author lk
 * @date 2019/1/8 11 25
 */
public interface IVideoLoadListener {
    void videoSuccess(List<VideoBean> videoBeans, List<TodayContentBean> contentBeans);
    void fail(Throwable throwable);
}
