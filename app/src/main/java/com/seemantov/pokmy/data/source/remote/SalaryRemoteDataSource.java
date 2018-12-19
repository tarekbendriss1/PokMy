package com.seemantov.pokmy.data.source.remote;

import com.seemantov.pokmy.data.source.remote.params.SalaryAdvanceParam;
import com.seemantov.pokmy.data.source.remote.params.SalaryParam;
import com.seemantov.pokmy.data.source.remote.response.NewsResponse;
import com.seemantov.pokmy.data.source.remote.response.SalaryResponse;
import com.seemantov.pokmy.data.source.remote.service.ServiceEndpoint;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class SalaryRemoteDataSource {

    private final ServiceEndpoint serviceEndpoint;

    @Inject
    public SalaryRemoteDataSource(ServiceEndpoint serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    /*
        public Single<List<MessageDb>> getMessages(int id) {
            return serviceEndpoint.getMedecinMessages(id);
        }
    */
    public Single<List<SalaryResponse>> getSalaries(SalaryParam param) {
        return serviceEndpoint.getSalaries(param);
    }

    public Single<String> addSalaryAdvance(SalaryAdvanceParam param) {
        return serviceEndpoint.addSalaryAdvance(param);
    }

}
