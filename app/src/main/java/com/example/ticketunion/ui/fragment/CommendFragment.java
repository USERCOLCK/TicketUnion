package com.example.ticketunion.ui.fragment;

import android.view.View;

import com.example.ticketunion.R;
import com.example.ticketunion.base.BaseFragment;

public class CommendFragment extends BaseFragment {
    @Override
    protected int getRootViewId() {
        return R.layout.fragment_commend;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }
}
