package com.example.ticketunion.base;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.example.ticketunion.databinding.FragmentCommendBinding;
import com.example.ticketunion.ui.fragment.HomeFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private Unbinder mBind;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater, container, savedInstanceState);
        mBind = ButterKnife.bind(this,rootView);
        initView(rootView);
        loadData();
        initPresenter();
        return rootView;

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

    protected View loadRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        int resId = getRootViewId();
        return inflater.inflate(resId,container,false);
    }

    protected abstract int getRootViewId();
}
