package com.example.ticketunion.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ticketunion.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    
    private State currentState = State.NONE;
    public enum State{
        NONE,LOADING,SUCCESS,ERROR,EMPTY;
    }
    private Unbinder mBind;
    private FrameLayout mBaseContainer;
    private  View successView;
    private View loadingView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.base_fragment_layout,container,false);
        mBaseContainer = rootView.findViewById(R.id.base_container);
        loadStatesView(inflater,container);
        mBind = ButterKnife.bind(this,rootView);
        initView(rootView);
        initPresenter();
        loadData();
        return rootView;

    }
      /**
       * 加载各种状态的View
      * */
    private void loadStatesView(LayoutInflater inflater, ViewGroup container) {
        successView = loadSuccessView(inflater, container);
        mBaseContainer.addView(successView);

        loadingView = loadLoadingView(inflater,container);
        mBaseContainer.addView(loadingView);
        setUpState(State.LOADING);
    }

    public void setUpState(State state){
        this.currentState = state;
        if (currentState == State.SUCCESS){
            successView.setVisibility(View.VISIBLE);
        }else {
            successView.setVisibility(View.GONE);
        }

        if (currentState == State.LOADING){
            loadingView.setVisibility(View.VISIBLE);
        }else {
            loadingView.setVisibility(View.GONE);
        }
    }

    protected View loadLoadingView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_loading,container,false);
    }

    protected void initView(View rootView){
        
    }

    //取消注册
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mBind!=null){
            mBind.unbind();
        }
        release();
    }

    protected void release() {
        //释放资源
    }

    protected void initPresenter() {
        //创建Presenter;
    }

    protected void loadData() {
        //加载数据
    }

    protected View loadSuccessView(LayoutInflater inflater, ViewGroup container){
        int resId = getRootViewId();
        return inflater.inflate(resId,container,false);
    }

    protected abstract int getRootViewId();
}
