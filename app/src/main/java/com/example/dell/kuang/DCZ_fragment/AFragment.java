package com.example.dell.kuang.DCZ_fragment;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.example.dell.kuang.DCZ_bean.StatusBean;
import com.example.dell.kuang.DCZ_okhttp.HttpHelper;
import com.example.dell.kuang.DCZ_okhttp.HttpServiceClient;
import com.example.dell.kuang.DCZ_okhttp.StatusBarUtil;
import com.example.dell.kuang.DCZ_util.DialogUtil;
import com.example.dell.kuang.R;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AFragment extends Fragment {
    private View view;
    private Dialog dialog;
    private AFragment INSTANCE;
    private int pageNumber=1;
    private int pageSize=5;
    private AnimationDrawable animationDrawable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StatusBarUtil.setImgTransparent(getActivity());      //这行是让标题沉浸
        view = View.inflate(getActivity(), R.layout.fragment_a, null);
        ButterKnife.bind(this, view);
        INSTANCE=this;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setViews();
        setListener();

    }

    private void setAnimation(int id,View iv){
        Animation operatingAnim = AnimationUtils.loadAnimation(getActivity(), id);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        if (operatingAnim != null) {
            iv.startAnimation(operatingAnim);
        }
    }

    /**
     * 懒加载
     * */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i("dcz_AF","正在执行setUserVisibleHint");
        if(isVisibleToUser){
            Log.i("dcz_AF","正在显示此页面");
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("dcz_AF","正在执行onHiddenChanged");
    }
    /**
     *      初始化
     *
     * */
    private void setViews() {
        getData();
    }
    /**
     *      监听
     *
     * */
    private void setListener() {

    }

    /***
     * 调取接口拿到服务器数据
     * */
    public void getData(){
        dialog= DialogUtil.createLoadingDialog(getActivity(),getString(R.string.loaddings),"2");
        dialog.show();
        HttpServiceClient.getInstance().getNews("",pageNumber,pageSize).enqueue(new Callback<StatusBean>() {
            @Override
            public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                dialog.dismiss();
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        Toast.makeText(getActivity(), "未知错误", Toast.LENGTH_SHORT).show();
                    }else {
                        Log.d("dcz","返回的数据是空的");
                    }
                }else {
                    Log.d("dcz","获取数据失败");
                }
            }
            @Override
            public void onFailure(Call<StatusBean> call, Throwable t) {
                Toast.makeText(getActivity(), HttpHelper.handleException(t), Toast.LENGTH_SHORT).show();
             /*   //在这里做全局的错误处理
                if (e instanceof ConnectException || e instanceof SocketTimeoutException || e instanceof TimeoutException) {
                //网络错误
                 //   _onError(-9999);
                } else if (e instanceof ResultException) {
                //自定义的ResultException
                //由于返回200,300返回格式不统一的问题，自定义GsonResponseBodyConverter凡是300的直接抛异常
                   // _onError(((ResultException) e).getErrCode());
                  //  System.out.println("---------errorCode------->"+((ResultException) e).getErrCode());
                }*/
            }
        });
    }
}
