package com.seemantov.pokmy.pokmy.home;

import android.app.Application;
import android.support.annotation.NonNull;

import com.seemantov.pokmy.App;
import com.seemantov.pokmy.base.BaseViewModel;
import com.seemantov.pokmy.data.source.remote.LeavesRemoteDataSource;
import com.seemantov.pokmy.data.source.remote.params.ExpensesParam;
import com.seemantov.pokmy.data.source.remote.params.LeaveParam;
import com.seemantov.pokmy.data.source.remote.params.SalaryParam;
import com.seemantov.pokmy.data.source.remote.response.LeaveResponse;
import com.seemantov.pokmy.data.source.remote.response.SalaryResponse;
import com.seemantov.pokmy.data.source.repository.LeavesRepository;
import com.seemantov.pokmy.data.source.repository.SalaryRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LeavesViewModel extends BaseViewModel {

    @Inject
    LeavesRepository leavesRepository;

    // constructor
    public LeavesViewModel(@NonNull Application application) {
        super(application);
        App.getDataComponent().inject(this);
    }


    public Single<List<LeaveResponse>> getLeaves(SalaryParam param) {
        return leavesRepository.getLeaves(param)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<String> addLeave(LeaveParam param) {
        return leavesRepository.addLeave(param)
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }





}