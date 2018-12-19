package com.seemantov.pokmy.data.source.remote;

import com.seemantov.pokmy.data.source.remote.params.LoginParams;
import com.seemantov.pokmy.data.source.remote.response.LoginPokResponse;
import com.seemantov.pokmy.data.source.remote.service.ServiceEndpoint;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Tarek Ben Driss on 02/03/2018.
 */

@Singleton
public class UserRemoteDataSource {


    private final ServiceEndpoint serviceEndpoint;

    @Inject
    public UserRemoteDataSource(ServiceEndpoint serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    // signup patient ws



    // signin ws


    public Single<LoginPokResponse> signinPok(LoginParams lp) {
        return serviceEndpoint.signinPok(lp);
    }

}
