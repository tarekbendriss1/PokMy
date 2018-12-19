package com.seemantov.pokmy.data.source.remote.service;

import com.seemantov.pokmy.data.source.remote.params.CompanyParam;
import com.seemantov.pokmy.data.source.remote.params.ExpensesParam;
import com.seemantov.pokmy.data.source.remote.params.LeaveParam;
import com.seemantov.pokmy.data.source.remote.params.LoginParams;
import com.seemantov.pokmy.data.source.remote.params.SalaryAdvanceParam;
import com.seemantov.pokmy.data.source.remote.params.SalaryParam;
import com.seemantov.pokmy.data.source.remote.response.CompanyResponse;
import com.seemantov.pokmy.data.source.remote.response.LabelResponse;
import com.seemantov.pokmy.data.source.remote.response.LeaveResponse;
import com.seemantov.pokmy.data.source.remote.response.LoginPokResponse;
import com.seemantov.pokmy.data.source.remote.response.NewsResponse;
import com.seemantov.pokmy.data.source.remote.response.SalaryResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceEndpoint {

    String BASE_URL = "https://demo.pokmy.net/";

    String LOGIN_POK = "api/auth/login";
    String MY_NEWS = "api/mobile/news";
    String ADD_EXPENSES = "api/mobile/expenseReports/create";
    String ADD_LEAVE = "api/mobile/leaves/create";
    String MY_SALARIES = "api/mobile/salaryAdvances/search";
    String LEAVES = "api/mobile/leaves/search";
    String GET_PRIORITIES = "api/mobile/priorities";
    String GET_LEAVES_TYPE = "api/mobile/leaveTypes";
    String GET_REPORT_TYPES = "api/mobile/expenseReportTypes";
    String GET_COMPANIES = "api/mobile/companies/search";
    String ADD_SALARY_ADVANCE = "api/mobile/salaryAdvances/create";


    /*Params*/

    String USERNAME = "username";
    String AUTO = "auto";


    @POST(LOGIN_POK)
    Single<LoginPokResponse> signinPok(@Body LoginParams loginParams);

    @POST(MY_NEWS)
    Single<List<NewsResponse>> getNews();

    @POST(MY_SALARIES)
    Single<List<SalaryResponse>> getSalaries(@Body SalaryParam param);

    @POST(LEAVES)
    Single<List<LeaveResponse>> getLeaves(@Body SalaryParam param);

    @POST(ADD_EXPENSES)
    Single<String> addExpense(@Body ExpensesParam param);

    @POST(ADD_LEAVE)
    Single<String> addLeave(@Body LeaveParam param);

    @POST(ADD_LEAVE)
    Single<String> addSalaryAdvance(@Body SalaryAdvanceParam param);

    @GET(GET_PRIORITIES)
    Single<List<LabelResponse>> getPriorities();

    @GET(GET_LEAVES_TYPE)
    Single<List<LabelResponse>> getLeavesType();

    @GET(GET_REPORT_TYPES)
    Single<List<LabelResponse>> getReportType();

    @POST(GET_COMPANIES)
    Single<List<CompanyResponse>> getCompanies(@Body CompanyParam param);

}
