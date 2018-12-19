package com.seemantov.pokmy.data.source.remote;

import com.seemantov.pokmy.data.source.local.entity.UploadedBy;
import com.seemantov.pokmy.data.source.remote.params.CompanyParam;
import com.seemantov.pokmy.data.source.remote.params.ExpensesParam;
import com.seemantov.pokmy.data.source.remote.response.CompanyResponse;
import com.seemantov.pokmy.data.source.remote.response.LabelResponse;
import com.seemantov.pokmy.data.source.remote.service.ServiceEndpoint;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;



@Singleton
public class ExpensesRemoteDataSource {

    private final ServiceEndpoint serviceEndpoint;

    @Inject
    public ExpensesRemoteDataSource(ServiceEndpoint serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    /*
        public Single<List<MessageDb>> getMessages(int id) {
            return serviceEndpoint.getMedecinMessages(id);
        }
    */
    public Single<String> addExpense(ExpensesParam param) {
        return serviceEndpoint.addExpense(param);
    }

    public Single<List<LabelResponse>> getPriorities() {
        return serviceEndpoint.getPriorities();
    }

    public Single<List<LabelResponse>> getLeavesType() {
        return serviceEndpoint.getLeavesType();
    }

    public Single<List<LabelResponse>> getReportType() {
        return serviceEndpoint.getReportType();
    }

    public Single<List<CompanyResponse>> getCompanies(CompanyParam param) {
        return serviceEndpoint.getCompanies(param);
    }


}
