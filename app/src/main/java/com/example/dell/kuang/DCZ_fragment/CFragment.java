package com.example.dell.kuang.DCZ_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.kuang.DCZ_okhttp.StatusBarUtil;
import com.example.dell.kuang.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.ButterKnife;

public class CFragment extends Fragment {
    private View view;
    private RefreshLayout refreshLayout;
    private ClassicsFooter footer;
    static {
        ClassicsFooter.REFRESH_FOOTER_PULLUP = "上拉加载更多2";
        ClassicsFooter.REFRESH_FOOTER_RELEASE = "释放立即加载2";
        ClassicsFooter.REFRESH_FOOTER_REFRESHING = "正在刷新2...";
        ClassicsFooter.REFRESH_FOOTER_LOADING = "正在加载2...";
        ClassicsFooter.REFRESH_FOOTER_FINISH = "加载完成2";
        ClassicsFooter.REFRESH_FOOTER_FAILED = "加载失败2";
        ClassicsFooter.REFRESH_FOOTER_ALLLOADED = "全部加载完成2";
    }
    static {
        ClassicsHeader.REFRESH_HEADER_PULLDOWN = "下拉可以刷新";
        ClassicsHeader.REFRESH_HEADER_REFRESHING = "正在刷新...";
        ClassicsHeader.REFRESH_HEADER_LOADING = "正在加载...";
        ClassicsHeader.REFRESH_HEADER_RELEASE = "释放立即刷新";
        ClassicsHeader.REFRESH_HEADER_FINISH = "刷新完成";
        ClassicsHeader.REFRESH_HEADER_FAILED = "刷新失败";
        ClassicsHeader.REFRESH_HEADER_LASTTIME = "上次更新 M-d HH:mm";
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StatusBarUtil.setImgTransparent(getActivity());      //这行是让标题沉浸
        view = View.inflate(getActivity(), R.layout.fragment_c, null);
        ButterKnife.bind(this, view);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        footer = view.findViewById(R.id.footer);
        setListener();
        return view;
    }

    private void setListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(200);
                refreshlayout.setLoadmoreFinished(true);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
