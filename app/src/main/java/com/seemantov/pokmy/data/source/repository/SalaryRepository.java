package com.seemantov.pokmy.data.source.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.seemantov.pokmy.data.source.remote.NewsRemoteDataSource;
import com.seemantov.pokmy.data.source.remote.SalaryRemoteDataSource;
import com.seemantov.pokmy.data.source.remote.params.SalaryAdvanceParam;
import com.seemantov.pokmy.data.source.remote.params.SalaryParam;
import com.seemantov.pokmy.data.source.remote.response.NewsResponse;
import com.seemantov.pokmy.data.source.remote.response.SalaryResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;



@Singleton
public class SalaryRepository {

    private final SalaryRemoteDataSource salariesRemoteDataSource;

    @Inject
    SalaryRepository( @NonNull SalaryRemoteDataSource salariesRemoteDataSource) {
        this.salariesRemoteDataSource = salariesRemoteDataSource;
    }

    public Single<List<SalaryResponse>> getSalaries(SalaryParam param) {
        return salariesRemoteDataSource.getSalaries(param)
                // oon Succes do in other thread
                .doOnSuccess(response -> {
                    Log.e("dsf","");
                });
    }

    public Single<String> addSalaryAdvance(SalaryAdvanceParam param) {
        return salariesRemoteDataSource.addSalaryAdvance(param)
                // oon Succes do in other thread
                .doOnSuccess(response -> {
                    Log.e("dsf","");
                });
    }

}
