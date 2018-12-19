package com.seemantov.pokmy.data.source.remote;

import com.seemantov.pokmy.data.source.remote.params.LeaveParam;
import com.seemantov.pokmy.data.source.remote.params.SalaryParam;
import com.seemantov.pokmy.data.source.remote.response.LeaveResponse;
import com.seemantov.pokmy.data.source.remote.response.SalaryResponse;
import com.seemantov.pokmy.data.source.remote.service.ServiceEndpoint;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;


@Singleton
public class LeavesRemoteDataSource {

    private final ServiceEndpoint serviceEndpoint;

    @Inject
    public LeavesRemoteDataSource(ServiceEndpoint serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    /*
        public Single<List<MessageDb>> getMessages(int id) {
            return serviceEndpoint.getMedecinMessages(id);
        }
    */
    public Single<List<LeaveResponse>> getLeaves(SalaryParam param) {
        return serviceEndpoint.getLeaves(param);
    }

    public Single<String> addLeave(LeaveParam param) {
        return serviceEndpoint.addLeave(param);
    }

}
