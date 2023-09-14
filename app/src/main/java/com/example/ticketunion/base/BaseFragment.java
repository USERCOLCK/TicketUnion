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
    //定义五种网络状态
    public enum State{
        NONE,LOADING,SUCCESS,ERROR,EMPTY;
    }
    private Unbinder mBind;
    private FrameLayout mBaseContainer;
    private  View mSuccessView;
    private View mLoadingView;
    private View mLoadErrorView;
    private View mLoadEmptyView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater,container);
        mBaseContainer = rootView.findViewById(R.id.base_container);
        loadStatesView(inflater,container);
        mBind = ButterKnife.bind(this,rootView);
        initView(rootView);
        initPresenter();
        loadData();
        return rootView;

    }

    protected  View loadRootView(LayoutInflater inflater, ViewGroup container){
        return inflater.inflate(R.layout.base_fragment_layout,container,false);
    }

    /**
       * 加载各种状态的View
      * */
    private void loadStatesView(LayoutInflater inflater, ViewGroup container) {
        //加载成功的View
        mSuccessView = loadSuccessView(inflater, container);
        mBaseContainer.addView(mSuccessView);
        //加载loading的View
        mLoadingView = loadLoadingView(inflater,container);
        mBaseContainer.addView(mLoadingView);
        //加载失败的View
        mLoadErrorView = loadErrorView(inflater,container);
        mBaseContainer.addView(mLoadErrorView);
        //加载内容为空的View
        mLoadEmptyView = loadEmptyView(inflater,container);
        mBaseContainer.addView(mLoadEmptyView);
        setUpState(State.NONE);
    }



      /**
      *  子类通过调用该方法来切换状态页面
      * */
    public void setUpState(State state){
        this.currentState = state;
        mSuccessView.setVisibility(currentState == State.SUCCESS ? View.VISIBLE : View.GONE);
        mLoadingView.setVisibility(currentState == State.LOADING ? View.VISIBLE : View.GONE);
        mLoadErrorView.setVisibility(currentState == State.ERROR ? View.VISIBLE : View.GONE);
        mLoadEmptyView.setVisibility(currentState == State.EMPTY ? View.VISIBLE : View.GONE);
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

    /**
     * 加载成功的界面
     * */
    protected View loadSuccessView(LayoutInflater inflater, ViewGroup container){
        int resId = getRootViewId();
        return inflater.inflate(resId,container,false);
    }
    /**
    * 加载Loading的界面
    * */
    protected View loadLoadingView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_loading,container,false);
    }
    /**
    *  加载失败的界面
    * */
    protected View loadErrorView(LayoutInflater inflater, ViewGroup container) {
        return  inflater.inflate(R.layout.fragment_error,container,false);
    }
    /**
    *  加载内容为空的界面
    * */
    protected View loadEmptyView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_empty,container,false);
    }

    protected abstract int getRootViewId();
}
