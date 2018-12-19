package com.seemantov.pokmy.pokmy.home;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.seemantov.pokmy.R;
import com.seemantov.pokmy.data.source.local.entity.News;


public class NewsItem extends BaseObservable {

    private final Context context;
    private News news;


    public Context getContext() {
        return context;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public NewsItem(Context context, News news) {
        this.context = context;
        this.news = news;

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
