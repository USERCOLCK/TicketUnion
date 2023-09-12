package com.example.ticketunion.view;

import com.example.ticketunion.model.domain.Categories;

public interface IHomeCallBack {
    void onCategoriesLoaded(Categories categories);
    void onError();
    void onEmpty();
    void onLoad();
}
