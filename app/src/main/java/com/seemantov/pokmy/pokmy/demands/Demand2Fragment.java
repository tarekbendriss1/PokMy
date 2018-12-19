package com.seemantov.pokmy.pokmy.demands;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.seemantov.pokmy.App;
import com.seemantov.pokmy.R;
import com.seemantov.pokmy.base.BaseFragment;
import com.seemantov.pokmy.data.source.local.entity.AssetExpense;
import com.seemantov.pokmy.data.source.remote.params.CompanyParam;
import com.seemantov.pokmy.data.source.remote.params.ExpensesParam;
import com.seemantov.pokmy.data.source.remote.response.CompanyResponse;
import com.seemantov.pokmy.data.source.remote.response.LabelResponse;
import com.seemantov.pokmy.pokmy.DashboardActivity;
import com.seemantov.pokmy.utils.AlertDialogUtils;
import com.seemantov.pokmy.utils.Logger;
import com.seemantov.pokmy.utils.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class Demand2Fragment extends BaseFragment {

    private TextView eText;
    private DatePickerDialog picker;
    private Button cancel,valid;
    private ImageView camera,file;
    private CompositeDisposable mDisposable;
    private ExpensesViewModel expensesViewModel;

    private List<String> priorities =  new ArrayList<>();
    private List<String> prioritiesID =  new ArrayList<>();
    private List<String> reportType =  new ArrayList<>();
    private List<String> reportTypeID =  new ArrayList<>();
    private List<String> companies = new ArrayList<>();
    private List<String> companiesID = new ArrayList<>();

    private EditText amount,etCompany,description;
    private ArrayAdapter<String> adapterPriorities;
    private ArrayAdapter<String> adapterReportType;
    private ArrayAdapter<String> companiesAdapter;
    private AssetExpense assetExpense;
    private ExpensesParam param;

    private ListView listView;
    private Spinner sPriorities,sReportType;


    public Demand2Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mission2, container, false);
        init(view);
        getPriorities();
        getReportType();
        return view;
    }

    private void init(View view)
    {
        mDisposable = new CompositeDisposable();
        expensesViewModel = ViewModelProviders.of(this).get(ExpensesViewModel.class);

        adapterPriorities = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, priorities);
        adapterReportType = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, reportType);
        companiesAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_list_item_1, companies);

        param = new ExpensesParam();
        assetExpense = new AssetExpense();

        adapterPriorities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterReportType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cancel = view.findViewById(R.id.cancel);
        valid = view.findViewById(R.id.valid);
        eText= view.findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        camera = view.findViewById(R.id.camera);
        file = view.findViewById(R.id.file);
        amount = view.findViewById(R.id.editText2);
        etCompany = view.findViewById(R.id.etCompany);
        description = view.findViewById(R.id.description);

        sPriorities = view.findViewById(R.id.spinner2);
        sReportType = view.findViewById(R.id.spinner1);
        listView = view.findViewById(R.id.companies);

        sPriorities.setAdapter(adapterPriorities);
        sReportType.setAdapter(adapterReportType);
        listView.setAdapter(companiesAdapter);


        sPriorities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                param.setPriority(prioritiesID.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sReportType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                assetExpense.setExpenseReportType(reportTypeID.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        eText.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(mContext,
                    (view16, year1, monthOfYear, dayOfMonth) -> {
                        eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                        //assetExpense.setDate(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                        assetExpense.setDate(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth+"T00:00:00.000Z");
                    }, year, month, day);
            picker.show();
        });

        cancel.setOnClickListener(view15 -> {
            Intent intent = new Intent(getActivity(),DashboardActivity.class);
            startActivity(intent);
        });

        valid.setOnClickListener(view14 ->
        {
            if (isValid())
                addExpenses();
            else
                AlertDialogUtils.show(mContext, mContext.getResources().getString(R.string.required_fields));
        });


        camera.setOnClickListener(view13 -> {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivity(intent);
        });

        file.setOnClickListener(view12 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"),0);
        });

        etCompany.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                companies.clear();
                companiesAdapter.notifyDataSetChanged();
                getCompanies(s.toString());
                etCompany.setHint(s.toString());
            }
        });

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            for (int i = 0; i < listView.getChildCount(); i++) {
                if(position == i ){
                    listView.getChildAt(i).setBackgroundColor(mContext.getResources().getColor(R.color.grispokmysplash));
                }else{
                    listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
            }
            assetExpense.setCompany(companiesID.get(position));
        });

    }

    private void addExpenses()
    {
        assetExpense.setAmount(Integer.parseInt(amount.getText().toString()));
        assetExpense.setPaymentMethod("5a5c8522c3f040167dc20984");
        assetExpense.setPayment("company");

        param.setAsset(assetExpense);
        param.setDescription(description.getText().toString());

        mDisposable.add(expensesViewModel.addExpenses(param)
                .subscribeOn(Schedulers.computation())
                .doOnSubscribe(disposable -> App.showLoader(mContext))
                .doFinally(App::hideLoader)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(String responses) {
                        Logger.e("RESULT",responses+"mriguel");
                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.e("RESULT","mouch mriguel "+error.getMessage() );
                    }
                }));
    }

    private void getPriorities()
    {
        mDisposable.add(expensesViewModel.getPriorities()
                .subscribeOn(Schedulers.computation())
                //.doOnSubscribe(disposable -> App.showLoader(mContext))
                //.doFinally(App::hideLoader)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<LabelResponse>>() {
                    @Override
                    public void onSuccess(List<LabelResponse> responses) {
                        for (LabelResponse lr: responses) {
                            Logger.e("LISTPRIORITIES",lr.toString());
                            priorities.add(lr.getLabel());
                            prioritiesID.add(lr.get_id());
                        }
                        adapterPriorities.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.e("LIST","mouch mriguel");
                    }
                }));
    }

    private void getReportType()
    {
        mDisposable.add(expensesViewModel.getReportType()
                .subscribeOn(Schedulers.computation())
                //.doOnSubscribe(disposable -> App.showLoader(mContext))
                //.doFinally(App::hideLoader)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<LabelResponse>>() {
                    @Override
                    public void onSuccess(List<LabelResponse> responses) {
                        for (LabelResponse lr: responses) {
                            Logger.e("LISTREPORT",lr.toString());
                            reportType.add(lr.getLabel());
                            reportTypeID.add(lr.get_id());
                        }
                        adapterReportType.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.e("LIST","mouch mriguel");
                    }
                }));
    }

    private void getCompanies(String s)
    {
        CompanyParam cp = new CompanyParam(s);
        mDisposable.add(expensesViewModel.getCompanies(cp)
                .subscribeOn(Schedulers.computation())
                //.doOnSubscribe(disposable -> App.showLoader(mContext))
                //.doFinally(App::hideLoader)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CompanyResponse>>() {
                    @Override
                    public void onSuccess(List<CompanyResponse> responses) {
                        for (CompanyResponse lr: responses) {
                            companies.add(lr.getName());
                            companiesID.add(lr.get_id());
                        }
                        companiesAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.e("LISTCOMPANIES","mouch mriguel "+error.getMessage());
                    }
                }));
    }

    private boolean isValid() {
        return StringUtils.isNotEmpty(etCompany.getText().toString()) && assetExpense!=null
                && StringUtils.isNotEmpty(amount.getText().toString()) && StringUtils.isNotEmpty(assetExpense.getDate())
                && StringUtils.isNotEmpty(description.getText().toString());
    }
}
