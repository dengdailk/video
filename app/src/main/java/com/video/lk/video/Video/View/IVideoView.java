package com.video.lk.video.Video.View;

import com.video.lk.video.Bean.TodayContentBean;

import java.util.List;

/**
 * File description
 *
 * @author lk
 * @date 2019/1/8 16 02
 */
public interface IVideoView {
    void showVideo(List<TodayContentBean> todayContentBeans,List<String> videoList);
    void hideDialog();
    void showDialog();
    void showErrorMsg(Throwable throwable);
}
