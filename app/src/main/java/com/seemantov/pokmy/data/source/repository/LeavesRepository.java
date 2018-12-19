package com.seemantov.pokmy.data.source.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.seemantov.pokmy.data.source.remote.LeavesRemoteDataSource;
import com.seemantov.pokmy.data.source.remote.NewsRemoteDataSource;
import com.seemantov.pokmy.data.source.remote.params.LeaveParam;
import com.seemantov.pokmy.data.source.remote.params.SalaryParam;
import com.seemantov.pokmy.data.source.remote.response.LeaveResponse;
import com.seemantov.pokmy.data.source.remote.response.NewsResponse;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;



@Singleton
public class LeavesRepository {

    private final LeavesRemoteDataSource leavesRemoteDataSource;

    @Inject
    LeavesRepository( @NonNull LeavesRemoteDataSource leavesRemoteDataSource) {
        this.leavesRemoteDataSource = leavesRemoteDataSource;
    }


    public Single<List<LeaveResponse>> getLeaves(SalaryParam param) {
        return leavesRemoteDataSource.getLeaves(param)
                // oon Succes do in other thread
                .doOnSuccess(response -> {
                });
    }

    public Single<String> addLeave(LeaveParam param) {
        return leavesRemoteDataSource.addLeave(param)
                // oon Succes do in other thread
                .doOnSuccess(response -> {
                });
    }
}
