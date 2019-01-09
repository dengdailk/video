package com.video.lk.video.Video;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.video.lk.video.Bean.TodayContentBean;
import com.video.lk.video.R;
import com.video.lk.video.Video.Presenter.IVideoPresenter;
import com.video.lk.video.Video.Presenter.VideoPresenter;
import com.video.lk.video.Video.View.IVideoView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment implements IVideoView {

    private IVideoPresenter iVideoPresenter;
    private RecyclerView rv_video;
    private ItemVideoAdapter itemVideoAdapter;
    private SwipeRefreshLayout srl_video;

    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iVideoPresenter = new VideoPresenter(this);
        rv_video = view.findViewById(R.id.rv_video);
        srl_video = view.findViewById(R.id.srl_video);
        srl_video.setColorSchemeColors(Color.parseColor("#ffce3d3a"));
        iVideoPresenter.loadVideo();
        srl_video.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iVideoPresenter.loadVideo();
            }
        });
        itemVideoAdapter = new ItemVideoAdapter(getActivity());
    }

    @Override
    public void showVideo(List<TodayContentBean> todayContentBeans, List<String> videoList) {
        itemVideoAdapter.setData(todayContentBeans, videoList);
        rv_video.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        rv_video.setAdapter(itemVideoAdapter);

    }

    @Override
    public void hideDialog() {
        srl_video.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        srl_video.setRefreshing(true);
    }

    @Override
    public void showErrorMsg(Throwable throwable) {
        Toast.makeText(getContext(), "加载出错:"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
