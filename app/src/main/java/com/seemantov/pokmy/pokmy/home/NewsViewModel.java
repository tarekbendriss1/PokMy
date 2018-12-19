package com.seemantov.pokmy.pokmy.home;

import android.app.Application;
import android.support.annotation.NonNull;

import com.seemantov.pokmy.App;
import com.seemantov.pokmy.base.BaseViewModel;
import com.seemantov.pokmy.data.source.remote.response.NewsResponse;
import com.seemantov.pokmy.data.source.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



public class NewsViewModel extends BaseViewModel {

    @Inject
    NewsRepository newsRepository;

    // constructor
    public NewsViewModel(@NonNull Application application) {
        super(application);
        App.getDataComponent().inject(this);
    }


    public Single<List<NewsResponse>> getNews() {
        return newsRepository.getNews()
                //
                .subscribeOn(Schedulers.computation())
                //
                .observeOn(AndroidSchedulers.mainThread());
    }




    /*
    public void getMessages(int id,Context context) {
        mDisposable.add(mRepository.getMessagesRemote(id)
                .doOnSubscribe(disposable -> App.showLoader(context))
                //hide loader on terminate to web service
                .doFinally(App::hideLoader)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies ->
                        {
                            App.hideLoader();
                            Logger.i("myPatientsTAG", movies);
                        },
                        error ->
                        {
                            App.hideLoader();
                            Logger.e("onError()", error.getMessage());
                        }
                ));
    }
    */



}
