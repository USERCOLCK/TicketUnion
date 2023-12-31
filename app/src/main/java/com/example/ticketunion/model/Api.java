package com.example.ticketunion.model;

import com.example.ticketunion.model.domain.Categories;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("discovery/categories")
    Call<Categories> getCategories();
}
