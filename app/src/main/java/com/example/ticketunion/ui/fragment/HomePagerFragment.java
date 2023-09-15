package com.example.ticketunion.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.ticketunion.R;
import com.example.ticketunion.base.BaseFragment;
import com.example.ticketunion.model.domain.Categories;
import com.example.ticketunion.utils.Constasts;
import com.example.ticketunion.utils.LogUtils;

public class HomePagerFragment extends BaseFragment {

    public static HomePagerFragment newInstance(Categories.DataBean category){
        HomePagerFragment homePagerFragment = new HomePagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constasts.KEY_HOME_PAGER_TITLE,category.getTitle());
        bundle.putInt(Constasts.KEY_HOME_PAGER_MATERIAL_ID, category.getId());
        homePagerFragment.setArguments(bundle);
        return homePagerFragment;
    }
    @Override
    protected int getRootViewId() {
        return R.layout.fragment_home_pager;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }

    @Override
    protected void loadData() {
        Bundle arguments = getArguments();
        String title = arguments.getString(Constasts.KEY_HOME_PAGER_TITLE);
        int materialId = arguments.getInt(Constasts.KEY_HOME_PAGER_MATERIAL_ID);
        //Todo:加载数据
        LogUtils.d(this,"title ------>"+title);
        LogUtils.d(this,"materialId ------>"+materialId);
    }
}
