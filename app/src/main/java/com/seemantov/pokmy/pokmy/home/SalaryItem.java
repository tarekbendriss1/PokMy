package com.seemantov.pokmy.pokmy.home;


import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.seemantov.pokmy.data.source.local.entity.News;
import com.seemantov.pokmy.data.source.local.entity.Salary;

public class SalaryItem extends BaseObservable {

    private final Context context;
    private Salary salary;


    public Context getContext() {
        return context;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public SalaryItem(Context context, Salary salary) {
        this.context = context;
        this.salary = salary;
    }

    public boolean onLongClick(View view) {
        //Toast.makeText(view.getContext(), "onLongClick", Toast.LENGTH_SHORT).show();
        return true;
    }

    public void onClick(View view) {
        /*
        Constants.ID_PATIENT=this.patient.getId();
        //AlertDialogUtils.show(view.getContext(), notification.getDetails());
        ItemMenuClickEvent item = new ItemMenuClickEvent(Constants.FICHE_PATIENT);
        EventBus.getDefault().post(item);
        */
    }

    public void onFicheClick(View view) {
        /*
        Constants.ID_PATIENT=this.patient.getId();
        //AlertDialogUtils.show(view.getContext(), notification.getDetails());
        ItemMenuClickEvent item = new ItemMenuClickEvent(Constants.FICHE_PATIENT);
        EventBus.getDefault().post(item);

        Constants.NOM_PATIENT = this.patient.getNom();
        Constants.ID_PATIENT = this.patient.getId();
        Constants.IMG_PATIENT = this.patient.getImage();
        Log.e("notifId",notifId+"");
        if(notifId!=0)
            EventBus.getDefault().post(new ItemMenuClickEvent(Constants.LIRE_NOTIF_MEDECIN,this.notifId));
        */

    }



    public void onSendClick(View view) {
        /*
        ItemMenuClickEvent item = new ItemMenuClickEvent(Constants.OPEN_INTERACTIONS);
        Constants.NOM_PATIENT = this.patient.getNom();
        Constants.ID_PATIENT = this.patient.getId();
        Constants.IMG_PATIENT = this.patient.getImage();
        EventBus.getDefault().post(item);

        if(notifId!=0)
            EventBus.getDefault().post(new ItemMenuClickEvent(Constants.LIRE_NOTIF_MEDECIN,this.notifId));
        */
    }



}
