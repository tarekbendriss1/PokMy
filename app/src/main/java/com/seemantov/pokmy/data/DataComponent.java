package com.seemantov.pokmy.data;

import com.seemantov.pokmy.AppModule;
import com.seemantov.pokmy.base.BaseGlideModule;
import com.seemantov.pokmy.data.source.local.database.StorageModule;
import com.seemantov.pokmy.data.source.remote.service.NetworkModule;
import com.seemantov.pokmy.data.source.remote.service.RequestInterceptor;

import com.seemantov.pokmy.pokmy.SplashScreenActivity;
import com.seemantov.pokmy.pokmy.demands.ExpensesViewModel;
import com.seemantov.pokmy.pokmy.home.LeavesViewModel;
import com.seemantov.pokmy.pokmy.home.NewsViewModel;
import com.seemantov.pokmy.pokmy.home.SalaryViewModel;
import com.seemantov.pokmy.pokmy.login.LoginActivity;
import com.seemantov.pokmy.pokmy.login.LoginViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, StorageModule.class, NetworkModule.class})
public interface DataComponent {

    void inject(BaseGlideModule baseGlideModule);
    void inject(RequestInterceptor resquestInterceptor);
    void inject(LoginActivity loginActivity);
    void inject(NewsViewModel newsViewModel);
    void inject(LoginViewModel loginViewModel);
    void inject(SalaryViewModel salaryViewModel);
    void inject(LeavesViewModel leavesViewModel);
    void inject(ExpensesViewModel expensesViewModel);
}
