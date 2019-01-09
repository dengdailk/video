package com.video.lk.video.Video.Model;

/**
 * File description
 *
 * @author lk
 * @date 2019/1/8 11 26
 */
public interface IVideoModel {
    void loadVideo(String category,IVideoLoadListener iVideoLoadListener);
}
