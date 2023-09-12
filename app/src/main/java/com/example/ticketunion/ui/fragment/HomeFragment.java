package com.example.ticketunion.ui.fragment;


import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.example.ticketunion.R;
import com.example.ticketunion.base.BaseFragment;
import com.example.ticketunion.model.domain.Categories;
import com.example.ticketunion.presenter.IHomePresent;
import com.example.ticketunion.presenter.impl.HomePresenterImpl;
import com.example.ticketunion.ui.adapter.HomePagerAdapter;
import com.example.ticketunion.utils.LogUtils;
import com.example.ticketunion.view.IHomeCallBack;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements IHomeCallBack {
    private IHomePresent mHomePresenter;
    private HomePagerAdapter mHomePageAdapter;
    @BindView(R.id.home_indicators)
    public TabLayout mTabLayout;
    @BindView(R.id.home_pager)
    public ViewPager homePager;
    @Override
    protected int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        //初始化各种控件
        mTabLayout.setupWithViewPager(homePager);
        //给ViewPager设置适配器
        mHomePageAdapter = new HomePagerAdapter(getChildFragmentManager());
        homePager.setAdapter(mHomePageAdapter);
    }

    @Override
    protected void initPresenter() {
        //创建Presenter
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.registerCallback(this);
    }

    @Override
    protected void loadData() {
        //加载数据
        if (mHomePresenter !=null){
            mHomePresenter.getCategories();
        }
    }

    @Override
    public void onCategoriesLoaded(Categories categories) {
        LogUtils.d(this,"onCategoriesLoaded.......");
        setUpState(State.SUCCESS);
        //加载的数据就会从这里回来
        if (mHomePageAdapter!=null){
            mHomePageAdapter.setCategories(categories);
        }
    }

    @Override
    public void onError() {
        setUpState(State.ERROR);
    }

    @Override
    public void onEmpty() {
        setUpState(State.EMPTY);
    }

    @Override
    public void onLoad() {
        setUpState(State.LOADING);
    }

    @Override
    protected void release() {
        //取消回调注册
        if (mHomePresenter != null) {
            mHomePresenter.unregisterCallback(this);
        }
    }
}
