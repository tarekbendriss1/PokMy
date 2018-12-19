package com.seemantov.pokmy.data.source.repository;

import android.support.annotation.NonNull;

import com.seemantov.pokmy.data.source.local.entity.UploadedBy;
import com.seemantov.pokmy.data.source.remote.ExpensesRemoteDataSource;
import com.seemantov.pokmy.data.source.remote.params.CompanyParam;
import com.seemantov.pokmy.data.source.remote.params.ExpensesParam;
import com.seemantov.pokmy.data.source.remote.response.CompanyResponse;
import com.seemantov.pokmy.data.source.remote.response.LabelResponse;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;


@Singleton
public class ExpensesRepository {

    private final ExpensesRemoteDataSource expensesRemoteDataSource;

    @Inject
    ExpensesRepository( @NonNull ExpensesRemoteDataSource expensesRemoteDataSource) {
        this.expensesRemoteDataSource = expensesRemoteDataSource;
    }


    public Single<String> addExpenses(ExpensesParam param) {
        return expensesRemoteDataSource.addExpense(param)
                // oon Succes do in other thread
                .doOnSuccess(response -> {
                });
    }

    public Single<List<LabelResponse>> getPriorities() {
        return expensesRemoteDataSource.getPriorities()
                // oon Succes do in other thread
                .doOnSuccess(response -> {
                });
    }

    public Single<List<LabelResponse>> getLeavesType() {
        return expensesRemoteDataSource.getLeavesType()
                // oon Succes do in other thread
                .doOnSuccess(response -> {
                });
    }

    public Single<List<LabelResponse>> getReportType() {
        return expensesRemoteDataSource.getReportType()
                // oon Succes do in other thread
                .doOnSuccess(response -> {
                });
    }

    public Single<List<CompanyResponse>> getCompanies(CompanyParam param) {
        return expensesRemoteDataSource.getCompanies(param)
                // oon Succes do in other thread
                .doOnSuccess(response -> {
                });
    }
}