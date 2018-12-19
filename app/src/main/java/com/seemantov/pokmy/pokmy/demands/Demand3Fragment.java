package com.seemantov.pokmy.pokmy.demands;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.seemantov.pokmy.App;
import com.seemantov.pokmy.R;
import com.seemantov.pokmy.base.BaseFragment;
import com.seemantov.pokmy.data.source.local.entity.SalaryAdvanceAsset;
import com.seemantov.pokmy.data.source.remote.params.SalaryAdvanceParam;
import com.seemantov.pokmy.data.source.remote.response.LabelResponse;
import com.seemantov.pokmy.pokmy.DashboardActivity;
import com.seemantov.pokmy.pokmy.home.SalaryViewModel;
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

public class Demand3Fragment extends BaseFragment {


    private TextView eText;
    private DatePickerDialog picker;
    private Button cancel, valid;
    private CompositeDisposable mDisposable;
    private ExpensesViewModel expensesViewModel;
    private SalaryViewModel salaryViewModel;
    private Spinner sPriorities;
    private List<String> priorities = new ArrayList<>();
    private List<String> prioritiesID = new ArrayList<>();
    private ArrayAdapter<String> adapterPriorities;
    private EditText amount, description;
    private SalaryAdvanceParam param;
    private SalaryAdvanceAsset asset;

    public Demand3Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mission3, container, false);

        init(view);
        getPriorities();

        return view;
    }


    private void init(View view) {
        mDisposable = new CompositeDisposable();
        expensesViewModel = ViewModelProviders.of(this).get(ExpensesViewModel.class);
        salaryViewModel = ViewModelProviders.of(this).get(SalaryViewModel.class);

        param = new SalaryAdvanceParam();
        asset = new SalaryAdvanceAsset();

        cancel = view.findViewById(R.id.cancel);
        valid = view.findViewById(R.id.valid);
        eText = view.findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        sPriorities = view.findViewById(R.id.spinner2);
        description = view.findViewById(R.id.description);
        amount = view.findViewById(R.id.editText2);

        adapterPriorities = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, priorities);
        adapterPriorities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sPriorities.setAdapter(adapterPriorities);

        eText.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(mContext,
                    (view13, year1, monthOfYear, dayOfMonth) -> {
                        eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                        //asset.setDate(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                        asset.setDate(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth+"T00:00:00.000Z");
                    }, year, month, day);
            picker.show();
        });

        sPriorities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                param.setPriority(prioritiesID.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cancel.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), DashboardActivity.class);
            startActivity(intent);
        });

        valid.setOnClickListener(view12 -> {
            if(isValid())
            {
                addSalaryAdvance();
            }
            else
                AlertDialogUtils.show(mContext, mContext.getResources().getString(R.string.required_fields));
        });

    }


    private void addSalaryAdvance() {

        asset.setAmount(Integer.parseInt(amount.getText().toString()));
        param.setAsset(asset);
        param.setDescription(description.getText().toString());

        mDisposable.add(salaryViewModel.addSalaryAdvance(param)
                .subscribeOn(Schedulers.computation())
                .doOnSubscribe(disposable -> App.showLoader(mContext))
                .doFinally(App::hideLoader)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<String>() {
                    @Override
                    public void onSuccess(String responses) {
                        Logger.e("RESULT", responses + "mriguel");
                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.e("RESULT", "mouch mriguel " + error.getMessage());
                    }
                }));
    }


    private void getPriorities() {
        mDisposable.add(expensesViewModel.getPriorities()
                .subscribeOn(Schedulers.computation())
                //.doOnSubscribe(disposable -> App.showLoader(mContext))
                //.doFinally(App::hideLoader)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<LabelResponse>>() {
                    @Override
                    public void onSuccess(List<LabelResponse> responses) {
                        for (LabelResponse lr : responses) {
                            Logger.e("LISTPRIORITIES", lr.toString());
                            priorities.add(lr.getLabel());
                            prioritiesID.add(lr.get_id());
                        }
                        adapterPriorities.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.e("LIST", "mouch mriguel");
                    }
                }));
    }

    private boolean isValid() {
        return StringUtils.isNotEmpty(description.getText().toString()) && asset!=null
                && StringUtils.isNotEmpty(amount.getText().toString()) && StringUtils.isNotEmpty(asset.getDate());
    }
}
