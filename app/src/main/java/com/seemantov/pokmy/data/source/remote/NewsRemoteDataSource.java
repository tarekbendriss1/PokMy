package com.seemantov.pokmy.data.source.remote;

import com.seemantov.pokmy.data.source.remote.response.NewsResponse;
import com.seemantov.pokmy.data.source.remote.service.ServiceEndpoint;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;


@Singleton
public class NewsRemoteDataSource {

    private final ServiceEndpoint serviceEndpoint;

    @Inject
    public NewsRemoteDataSource(ServiceEndpoint serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    /*
        public Single<List<MessageDb>> getMessages(int id) {
            return serviceEndpoint.getMedecinMessages(id);
        }
    */
    public Single<List<NewsResponse>> getNews() {
        return serviceEndpoint.getNews();
    }

}
