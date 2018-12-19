package com.seemantov.pokmy.pokmy.home;

import android.app.Application;
import android.support.annotation.NonNull;

import com.seemantov.pokmy.App;
import com.seemantov.pokmy.base.BaseViewModel;
import com.seemantov.pokmy.data.source.remote.params.SalaryAdvanceParam;
import com.seemantov.pokmy.data.source.remote.params.SalaryParam;
import com.seemantov.pokmy.data.source.remote.response.NewsResponse;
import com.seemantov.pokmy.data.source.remote.response.SalaryResponse;
import com.seemantov.pokmy.data.source.repository.NewsRepository;
import com.seemantov.pokmy.data.source.repository.SalaryRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



public class SalaryViewModel extends BaseViewModel {

    @Inject
    SalaryRepository salaryRepository;

    // constructor
    public SalaryViewModel(@NonNull Application application) {
        super(application);
        App.getDataComponent().inject(this);
    }


    public Single<List<SalaryResponse>> getSalaries(SalaryParam param) {
        return salaryRepository.getSalaries(param)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<String> addSalaryAdvance(SalaryAdvanceParam param) {
        return salaryRepository.addSalaryAdvance(param)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }






}
