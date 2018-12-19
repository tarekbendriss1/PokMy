package com.seemantov.pokmy.pokmy.demands;

import android.app.Application;
import android.support.annotation.NonNull;

import com.seemantov.pokmy.App;
import com.seemantov.pokmy.base.BaseViewModel;
import com.seemantov.pokmy.data.source.local.entity.UploadedBy;
import com.seemantov.pokmy.data.source.remote.params.CompanyParam;
import com.seemantov.pokmy.data.source.remote.params.ExpensesParam;
import com.seemantov.pokmy.data.source.remote.response.CompanyResponse;
import com.seemantov.pokmy.data.source.remote.response.LabelResponse;
import com.seemantov.pokmy.data.source.repository.ExpensesRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ExpensesViewModel extends BaseViewModel {

    @Inject
    ExpensesRepository expensesRepository;

    // constructor
    public ExpensesViewModel(@NonNull Application application) {
        super(application);
        App.getDataComponent().inject(this);
    }


    public Single<String> addExpenses(ExpensesParam param) {
        return expensesRepository.addExpenses(param)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Single<List<LabelResponse>> getPriorities() {
        return expensesRepository.getPriorities()
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Single<List<LabelResponse>> getLeavesType() {
        return expensesRepository.getLeavesType()
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<LabelResponse>> getReportType() {
        return expensesRepository.getReportType()
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Single<List<CompanyResponse>> getCompanies(CompanyParam param) {
        return expensesRepository.getCompanies(param)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }





}