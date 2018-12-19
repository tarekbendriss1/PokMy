package com.seemantov.pokmy.data.source.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.seemantov.pokmy.data.source.remote.NewsRemoteDataSource;
import com.seemantov.pokmy.data.source.remote.response.NewsResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;


@Singleton
public class NewsRepository {

    private final NewsRemoteDataSource newsRemoteDataSource;

    @Inject
    NewsRepository( @NonNull NewsRemoteDataSource newsRemoteDataSource) {
        this.newsRemoteDataSource = newsRemoteDataSource;
    }

    public Single<List<NewsResponse>> getNews() {
        return newsRemoteDataSource.getNews()
                .doOnSuccess(response -> {
                });
    }

}
