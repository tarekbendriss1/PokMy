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
import com.seemantov.pokmy.data.source.local.entity.AssetAddLeave;
import com.seemantov.pokmy.data.source.local.entity.StartEnd;
import com.seemantov.pokmy.data.source.remote.params.LeaveParam;
import com.seemantov.pokmy.data.source.remote.response.LabelResponse;
import com.seemantov.pokmy.pokmy.DashboardActivity;
import com.seemantov.pokmy.pokmy.home.LeavesViewModel;
import com.seemantov.pokmy.utils.AlertDialogUtils;
import com.seemantov.pokmy.utils.InternetUtils;
import com.seemantov.pokmy.utils.Logger;
import com.seemantov.pokmy.utils.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class Demand1Fragment extends BaseFragment {

    private TextView eText, eText2;
    private DatePickerDialog picker, picker2;
    private Button cancel, valider;
    private CompositeDisposable mDisposable;
    private ExpensesViewModel expensesViewModel;
    private List<String> priorities = new ArrayList<>();
    private List<String> prioritiesID = new ArrayList<>();
    private List<String> types = new ArrayList<>();
    private List<String> typesID = new ArrayList<>();
    private ArrayAdapter<String> adapterPriorities,leavesAdapter;
    private Spinner sPriorities, startDay, endDay, leaveType;
    private AssetAddLeave assetAddLeave;
    private LeavesViewModel leavesViewModel;
    private LeaveParam param;
    private StartEnd start, end;
    private EditText description;

    public Demand1Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mission1, container, false);
        init(view);
        getPriorities();
        getLeaveTypes();
        return view;
    }

    private void init(View view) {
        mDisposable = new CompositeDisposable();
        expensesViewModel = ViewModelProviders.of(this).get(ExpensesViewModel.class);
        leavesViewModel = ViewModelProviders.of(this).get(LeavesViewModel.class);
        adapterPriorities = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, priorities);
        adapterPriorities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leavesAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, types);
        leavesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sPriorities = view.findViewById(R.id.spinner2);
        description = view.findViewById(R.id.description);
        leaveType = view.findViewById(R.id.spinner1);
        assetAddLeave = new AssetAddLeave();
        start = new StartEnd();
        end = new StartEnd();

        sPriorities.setAdapter(adapterPriorities);
        leaveType.setAdapter(leavesAdapter);

        cancel = view.findViewById(R.id.cancel);
        startDay = view.findViewById(R.id.spinner3);
        endDay = view.findViewById(R.id.spinner4);
        valider = view.findViewById(R.id.valid);
        eText = view.findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        eText2 = view.findViewById(R.id.editText2);
        eText2.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(mContext,
                    (view13, year12, monthOfYear, dayOfMonth) -> {
                        eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year12);
                        //start.setDate(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year12);
                        start.setDate(year12 + "-" + (monthOfYear + 1) + "-" + dayOfMonth+"T00:00:00.000Z");
                    }, year, month, day);
            picker.show();
        });

        eText2.setOnClickListener(view1 -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            picker2 = new DatePickerDialog(mContext,
                    (view11, year1, monthOfYear, dayOfMonth) -> {
                        eText2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                        //end.setDate(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                        end.setDate(year1 + "-" + (monthOfYear + 1) + "-" + dayOfMonth+"T00:00:00.000Z");
                    }, year, month, day);
            picker2.show();
        });

        cancel.setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), DashboardActivity.class);
            startActivity(intent);
        });

        valider.setOnClickListener(v -> {
            if(isValid())
            {
                addLeave();
            }
            else
                AlertDialogUtils.show(mContext, mContext.getResources().getString(R.string.required_fields));
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

        leaveType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                assetAddLeave.setLeaveType(typesID.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        param = new LeaveParam();
    }

    private void addLeave() {
        start.setHalfDay(startDay.getSelectedItem().toString());
        end.setHalfDay(endDay.getSelectedItem().toString());

        assetAddLeave.setStart(start);
        assetAddLeave.setEnd(end);
        assetAddLeave.setLeaveType(leaveType.getSelectedItem().toString());

        param.setAsset(assetAddLeave);
        param.setDescription(description.getText().toString());

        mDisposable.add(leavesViewModel.addLeave(param)
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

    private void getLeaveTypes() {
        mDisposable.add(expensesViewModel.getLeavesType()
                .subscribeOn(Schedulers.computation())
                //.doOnSubscribe(disposable -> App.showLoader(mContext))
                //.doFinally(App::hideLoader)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<LabelResponse>>() {
                    @Override
                    public void onSuccess(List<LabelResponse> responses) {
                        for (LabelResponse lr : responses) {
                            Logger.e("LISTPRIORITIES", lr.toString());
                            types.add(lr.getLabel());
                            typesID.add(lr.get_id());
                        }
                        leavesAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.e("LIST", "mouch mriguel");
                    }
                }));
    }

    private boolean isValid() {
        return StringUtils.isNotEmpty(description.getText().toString()) && start!=null && end!=null
                && StringUtils.isNotEmpty(start.getDate()) && StringUtils.isNotEmpty(end.getDate());
    }
}
