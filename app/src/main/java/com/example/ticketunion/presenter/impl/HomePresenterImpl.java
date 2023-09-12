package com.example.ticketunion.presenter.impl;

import com.example.ticketunion.model.Api;
import com.example.ticketunion.model.domain.Categories;
import com.example.ticketunion.presenter.IHomePresent;
import com.example.ticketunion.utils.LogUtils;
import com.example.ticketunion.utils.RetrofitManager;
import com.example.ticketunion.view.IHomeCallBack;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomePresenterImpl implements IHomePresent {
    private IHomeCallBack mCallBack = null;

    @Override
    public void getCategories() {

        if (mCallBack != null){
            mCallBack.onLoad();
        }

        //加载分类数据
        Retrofit retrofit =RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<Categories> task = api.getCategories();
        task.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                int code = response.code();
                LogUtils.d(HomePresenterImpl.this,"result code is -->"+code);
                if (code == HttpURLConnection.HTTP_OK){
                    //请求成功
                    Categories categories = response.body();

                    if (mCallBack !=null){
                        if (categories == null || categories.getData().size() == 0){
                            mCallBack.onEmpty();
                        }else {
                            mCallBack.onCategoriesLoaded(categories);
                        }
                    }
//                    LogUtils.d(HomePresenterImpl.this,"jbc"+categories.toString());
                }else {
                    //请求失败
                    LogUtils.i(HomePresenterImpl.this,"请求失败。。。。");
                    if (mCallBack != null){
                        mCallBack.onError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                //加载失败的结果 
                LogUtils.e(HomePresenterImpl.this,"请求错误。。。。。。"+t);
                if (mCallBack != null){
                    mCallBack.onError();
                }
            }
        });
        
        
    }

    @Override
    public void registerCallback(IHomeCallBack callBack) {
        this.mCallBack = callBack;

    }

    @Override
    public void unregisterCallback(IHomeCallBack callBack) {
        mCallBack = null;
    }
}
