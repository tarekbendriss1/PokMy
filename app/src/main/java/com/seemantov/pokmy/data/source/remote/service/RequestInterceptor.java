package com.seemantov.pokmy.data.source.remote.service;

import android.support.annotation.NonNull;

import com.seemantov.pokmy.App;
import com.seemantov.pokmy.data.source.local.UserLocalDataSource;
import com.seemantov.pokmy.data.source.local.UserPOKLocalDataSource;
import com.seemantov.pokmy.utils.Logger;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    /*Params*/
    private static final String PAGE = "page";
    private static final String API_KEY = "api_key";
    private static final String LANGUAGE = "language";
    /* Headers */
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String AUTHORIZATION = "Authorization";
    private static final String ACCEPT = "accept";
    private static final String ACCEPT_ENCODING = "accept-encoding";
    private static final String ACCEPT_LANGUAGE = "accept-language";
    private static final String USER_AGENT = "user-agent";
    /* Values */
    private static final String CONTENT_JSON = "application/json";
    private static final String ENCODING_GZIP = "gzip, deflate";
    private static final String LANGUAGE_EN = "en-US,en;q=0.8";
    private static final String AGENT_THE_MOVIE_DB = "TheMovieDB";
    private static final String LANGUAGE_FR = "fr-FR";
    private static final String KEY = "dcc77f08dc635793aec422e0436bbdca";

    @Inject
    UserLocalDataSource userLocalDataSource;

    @Inject
    UserPOKLocalDataSource userPOKLocalDataSource;

    RequestInterceptor() {
        App.getDataComponent().inject(this);
    }


    public static Request provideRequest(@NonNull Request original, @NonNull Headers headers, @NonNull HttpUrl httpUrl) {
        Request.Builder requestBuilder = original.newBuilder()
                .headers(headers)
                //.url(httpUrl)
                .method(original.method(), original.body());
        return requestBuilder.build();
    }

    public static Headers provideHeaders(String token) {
        Headers.Builder headersBuilder = new Headers.Builder();
        headersBuilder.add(AUTHORIZATION, token);
        headersBuilder.add(ACCEPT, CONTENT_JSON);
        headersBuilder.add(CONTENT_TYPE, CONTENT_JSON);
        headersBuilder.add(ACCEPT_ENCODING, ENCODING_GZIP);
        headersBuilder.add(ACCEPT_LANGUAGE, LANGUAGE_FR);
        headersBuilder.add(USER_AGENT, AGENT_THE_MOVIE_DB);
        return headersBuilder.build();
    }



    public static HttpUrl provideHttpUrl(@NonNull Request original) {
        HttpUrl.Builder httpUrlBuilder = original.url().newBuilder()
                .addQueryParameter(API_KEY, KEY)
                .addQueryParameter(LANGUAGE, LANGUAGE_FR);
        return httpUrlBuilder.build();
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        String token = "";
        if(userPOKLocalDataSource.getConnectedUserToken() != null)
        {
            token = "Bearer " + userPOKLocalDataSource.getConnectedUserToken();
            Logger.i("token1",token);
        }
            //token = "Bearer " + userLocalDataSource.getConnectedUserToken();
        else
        {
            //token = "Bearer " + App.token;
            //Logger.i("token1"," mouch mriguel");
        }


        Logger.i("token2",App.token);

        //Logger.i("token1",token);


        final Request request = chain.request();
        final Headers headers = provideHeaders(token);
        final HttpUrl httpUrl = provideHttpUrl(request);
        final Request newRequest = provideRequest(request, headers, httpUrl);
        return chain.proceed(newRequest);
    }
}
