package com.seemantov.pokmy.pokmy;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.animation.OvershootInterpolator;

import com.seemantov.pokmy.App;
import com.seemantov.pokmy.R;
import com.seemantov.pokmy.base.BaseActivity;
import com.seemantov.pokmy.data.source.local.entity.Leave;
import com.seemantov.pokmy.data.source.local.entity.News;
import com.seemantov.pokmy.data.source.local.entity.Salary;
import com.seemantov.pokmy.data.source.remote.params.SalaryParam;
import com.seemantov.pokmy.data.source.remote.response.LeaveResponse;
import com.seemantov.pokmy.data.source.remote.response.NewsResponse;
import com.seemantov.pokmy.data.source.remote.response.SalaryResponse;
import com.seemantov.pokmy.pokmy.home.LeavesAdapter;
import com.seemantov.pokmy.pokmy.home.LeavesViewModel;
import com.seemantov.pokmy.pokmy.home.SalariesAdapter;
import com.seemantov.pokmy.pokmy.home.SalaryViewModel;
import com.seemantov.pokmy.pokmy.login.LoginActivity;
import com.seemantov.pokmy.pokmy.missions.MissionsActivity;
import com.seemantov.pokmy.pokmy.home.NewsAdapter;
import com.seemantov.pokmy.pokmy.home.NewsViewModel;
import com.seemantov.pokmy.pokmy.demands.DemandsActivity;
import com.seemantov.pokmy.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class DashboardActivity extends BaseActivity {

    private CompositeDisposable mDisposable;
    private RecyclerView newsRecycler,salariesRecycler,leavesRecycler;

    private List<News> news;
    private List<Salary> salaries;
    private List<Leave> leaves;

    private NewsAdapter mAdapter;
    private SalariesAdapter salariesAdapter;
    private LeavesAdapter leavesAdapter;

    private NewsViewModel newsViewModel;
    private SalaryViewModel salaryViewModel;
    private LeavesViewModel leavesViewModel;

    private LinearLayoutManager mLayoutManager,mLayoutManager2,mLayoutManager3;
    private SlideInUpAnimator mSlideInUpAnimator,mSlideInUpAnimator2,mSlideInUpAnimator3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                Intent intent1 = new Intent(DashboardActivity.this,DemandsActivity.class);
                Intent intent2 = new Intent(DashboardActivity.this,MissionsActivity.class);
                Intent intent3 = new Intent(DashboardActivity.this,LoginActivity.class);
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.mission:

                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("Missions");

                        String[] animals = {"Leave Request", "Expense Report", "Salary Advence"};
                        builder.setItems(animals, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                intent1.putExtra("f",i+"");
                                Logger.e("i=",i);
                                startActivity(intent1);
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();


                        return true;
                    case R.id.add:
                        startActivity(intent2);
                        return true;
                    case R.id.settings:
                        startActivity(intent3);
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
        initRecyclerView();
        getMyNews();
        getMySalaries();
        getLeaves();
    }

    private void init()
    {
        mDisposable = new CompositeDisposable();

        newsRecycler = findViewById(R.id.news);
        salariesRecycler = findViewById(R.id.dates);
        leavesRecycler = findViewById(R.id.missions);

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        salaryViewModel = ViewModelProviders.of(this).get(SalaryViewModel.class);
        leavesViewModel = ViewModelProviders.of(this).get(LeavesViewModel.class);

        news = new ArrayList<>();
        salaries = new ArrayList<>();
        leaves = new ArrayList<>();

        BottomNavigationView navigation =findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initRecyclerView() {
        mAdapter = new NewsAdapter();
        salariesAdapter = new SalariesAdapter();
        leavesAdapter = new LeavesAdapter();

        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager2 = new LinearLayoutManager(mContext);
        mLayoutManager3 = new LinearLayoutManager(mContext);

        newsRecycler.setLayoutManager(mLayoutManager);
        newsRecycler.setHasFixedSize(true);
        newsRecycler.setAdapter(mAdapter);

        salariesRecycler.setLayoutManager(mLayoutManager2);
        salariesRecycler.setHasFixedSize(true);
        salariesRecycler.setAdapter(salariesAdapter);


        leavesRecycler.setLayoutManager(mLayoutManager3);
        leavesRecycler.setHasFixedSize(true);
        leavesRecycler.setAdapter(leavesAdapter);


        mSlideInUpAnimator = new SlideInUpAnimator(new OvershootInterpolator(1f));
        mSlideInUpAnimator.setAddDuration(2000);
        mSlideInUpAnimator2 = new SlideInUpAnimator(new OvershootInterpolator(1f));
        mSlideInUpAnimator2.setAddDuration(4000);
        mSlideInUpAnimator3 = new SlideInUpAnimator(new OvershootInterpolator(1f));
        mSlideInUpAnimator3.setAddDuration(3000);

        newsRecycler.setItemAnimator(mSlideInUpAnimator);
        salariesRecycler.setItemAnimator(mSlideInUpAnimator2);
        leavesRecycler.setItemAnimator(mSlideInUpAnimator3);
    }

    private void getMyNews()
    {
        mDisposable.add(newsViewModel.getNews()
                //
                .subscribeOn(Schedulers.computation())
                //
                .doOnSubscribe(disposable -> App.showLoader(mContext))
                //hide loader on terminate to web service
                .doFinally(App::hideLoader)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<NewsResponse>>() {
                    @Override
                    public void onSuccess(List<NewsResponse> responses) {

                        for (NewsResponse nr: responses) {
                            news.add(new News(nr.get_id(), htmlToText(nr.getDescription()), nr.getLabel(), nr.getImages()));
                        }
                        mAdapter.change(news);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.e("NewsTag", error);
                        App.hideLoader();
                        Intent intent = new Intent(DashboardActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }));
    }

    private String htmlToText(String s)
    {
        s=s.replaceAll("<p>","");
        s=s.replaceAll("</p>"," ");
        s=s.replaceAll("<b>","");
        s=s.replaceAll("</b>","");
        s=s.replaceAll("<br>","\n");

        return s;
    }

    private void getMySalaries()
    {
        SalaryParam param = new SalaryParam();
        param.setDateFrom("");
        param.setDateTo("");
        param.setLimit(0);

        mDisposable.add(salaryViewModel.getSalaries(param)
                .subscribeOn(Schedulers.computation())
                //.doOnSubscribe(disposable -> App.showLoader(mContext))
                //.doFinally(App::hideLoader)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<SalaryResponse>>() {
                    @Override
                    public void onSuccess(List<SalaryResponse> responses) {
                        for (SalaryResponse sl: responses) {
                            salaries.add(new Salary(sl.get_id(), sl.getActivityState(), sl.getAsset()));
                        }
                        salariesAdapter.change(salaries);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.e("SalariesTag", error);
                        Intent intent = new Intent(DashboardActivity.this,LoginActivity.class);
                        startActivity(intent);
                        }
                }));
    }

    private void getLeaves()
    {
        SalaryParam param = new SalaryParam();
        param.setDateFrom("");
        param.setDateTo("");
        param.setLimit(0);

        mDisposable.add(leavesViewModel.getLeaves(param)
                .subscribeOn(Schedulers.computation())
                //.doOnSubscribe(disposable -> App.showLoader(mContext))
                //.doFinally(App::hideLoader)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<LeaveResponse>>() {
                    @Override
                    public void onSuccess(List<LeaveResponse> responses) {
                        for (LeaveResponse lv: responses) {
                            leaves.add(new Leave(lv.getId(), lv.getDescription(), lv.getActivityStateId(),lv.getActivityStateLabel(),lv.getType(),lv.getCreatedBy(),lv.getAsset()));
                        }
                        leavesAdapter.change(leaves);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.e("LeavesTag", error);
                        Intent intent = new Intent(DashboardActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }));
    }



}
