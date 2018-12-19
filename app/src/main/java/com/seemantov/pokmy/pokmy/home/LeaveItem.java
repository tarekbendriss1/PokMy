package com.seemantov.pokmy.pokmy.home;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.seemantov.pokmy.data.source.local.entity.Leave;
import com.seemantov.pokmy.data.source.local.entity.News;

public class LeaveItem extends BaseObservable {

    private final Context context;
    private Leave leave;


    public Context getContext() {
        return context;
    }

    public LeaveItem(Context context, Leave leave) {
        this.context = context;
        this.leave = leave;
    }

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }

    public boolean onLongClick(View view) {
        return true;
    }

    public void onClick(View view) {

    }

    public void onFicheClick(View view) {
    }



    public void onSendClick(View view) {
    }



}
