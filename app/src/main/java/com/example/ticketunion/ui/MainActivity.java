package com.example.ticketunion.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.ticketunion.R;
import com.example.ticketunion.base.BaseFragment;
import com.example.ticketunion.databinding.ActivityMainBinding;
import com.example.ticketunion.ui.fragment.CommendFragment;
import com.example.ticketunion.ui.fragment.HomeFragment;
import com.example.ticketunion.ui.fragment.RedPacketFragment;
import com.example.ticketunion.ui.fragment.SearchFragment;
import com.example.ticketunion.utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_navigation_bar)
    public BottomNavigationView mNavigationView;
//    private ActivityMainBinding binding;
    private HomeFragment mHomeFragment;
    private CommendFragment mCommendFragment;
    private RedPacketFragment mRedPacketFragment;
    private SearchFragment mSearchFragment;
    private Unbinder mBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        mBind = ButterKnife.bind(this);
        initFragment();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind!=null){
            mBind.unbind();

        }
    }

    private void initFragment() {
        mHomeFragment = new HomeFragment();
        mCommendFragment = new CommendFragment();
        mRedPacketFragment = new RedPacketFragment();
        mSearchFragment = new SearchFragment();
        switchFragment(mHomeFragment);
    }

    private void initListener() {
        mNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home){
                    LogUtils.d(MainActivity.this,"切换到首页");
                    switchFragment(mHomeFragment);
                } else if (item.getItemId() == R.id.commend) {
                    LogUtils.d(MainActivity.this,"切换到推荐");
                    switchFragment(mCommendFragment);
                } else if (item.getItemId() == R.id.red_packet) {
                    LogUtils.d(MainActivity.this,"切换到钱包");
                    switchFragment(mRedPacketFragment);
                } else if (item.getItemId() == R.id.search) {
                    LogUtils.d(MainActivity.this,"切换到搜索");
                    switchFragment(mSearchFragment);
                }
                return true;
            }
        });
    }

    private void switchFragment(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_page_container,fragment);
        fragmentTransaction.commit();
    }

}