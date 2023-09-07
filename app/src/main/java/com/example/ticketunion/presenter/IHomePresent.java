package com.example.ticketunion.presenter;

import com.example.ticketunion.view.IHomeCallBack;

public interface IHomePresent {
    /**
    *  获取商品分类
    * */
    void getCategories();
     /**
      * 注册UI的通知接口
      * */
    void registerCallback(IHomeCallBack callBack);
    /**
     * 取消注册UI的通知接口
     * */
    void unregisterCallback(IHomeCallBack callBack);
}
