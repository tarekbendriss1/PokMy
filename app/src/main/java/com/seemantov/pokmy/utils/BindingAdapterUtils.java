package com.seemantov.pokmy.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.seemantov.pokmy.App;

import com.seemantov.pokmy.R;
import com.seemantov.pokmy.base.GlideApp;
import com.seemantov.pokmy.data.source.local.converter.Converters;
import com.seemantov.pokmy.data.source.local.entity.Image;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.text.format.DateFormat;

import de.hdodenhof.circleimageview.CircleImageView;


public class BindingAdapterUtils {


    @BindingAdapter("url")
    public static void setImageUrl(ImageView view, String url) {
        RequestBuilder<Bitmap> requestBuilder = GlideApp.with(view.getContext()).asBitmap();
        requestBuilder.load(url).into(view);
    }


    @BindingAdapter("urlImg")
    public static void setImage(ImageView view, String url) {
        Context context = App.app.getApplicationContext();
        if(!Constants.IMG_PATIENT.equals("00000") && Constants.IMG_PATIENT.substring(0,4).equals("http"))
        GlideApp.with(context).load(Constants.IMG_PATIENT).placeholder(R.drawable.ic_doctor_avk).into(view);
        else
            GlideApp.with(context).load(R.drawable.ic_doctor_avk).into(view);


    }

    @BindingAdapter("imgPatient")
    public static void setImagePatient(CircleImageView view, String url) {
        Context context = App.app.getApplicationContext();
        //Log.e("imgPatientTAG",url);
        if(url!=null && !url.equals("00000") && !url.equals("") && url.substring(0,4).equals("http"))
        {
            GlideApp.with(context).load(url).placeholder(R.drawable.ic_doctor_avk).into(view);
            view.setBorderWidth(1);
        }
        else
        {
            GlideApp.with(context).load(R.drawable.ic_doctor_avk).into(view);
            view.setBorderWidth(0);
        }

    }


    @BindingAdapter("imgNews")
    public static void setImageNews(ImageView view, List<Image> image) {
        Context context = App.app.getApplicationContext();
        String url;
        //Log.e("imgPatientTAG",url);
        if(image!=null && image.get(0)!=null)
        {
            url = "https://demo.pokmy.net/api/documents/"+image.get(0).get_id()+"/resize/1/100/100/true?access_token="+App.token;
            GlideApp.with(context).applyDefaultRequestOptions(new RequestOptions().disallowHardwareConfig())
                    .load(url).apply(RequestOptions.circleCropTransform()).placeholder(R.drawable.empty).into(view);
        }
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("onLoadMore")
    public static void setRecyclerViewEndlessScroll(RecyclerView recyclerView, EndlessScroll endlessScroll) {
        endlessScroll = new EndlessScroll((LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
            }
        };
        recyclerView.addOnScrollListener(endlessScroll);
    }

    @BindingAdapter("backgroundMsg")
    public static void setBackgroundImg(LinearLayout layout, String s) {
            if(s.equals("patient"))
            {
                layout.setBackgroundResource(R.drawable.shape_outgoing_bubble);
            }
            else
                layout.setBackgroundResource(R.drawable.shape_incoming_bubble);
        Log.e("backgroundTAG",s);


    }

    @BindingAdapter("backgroundMedecin")
    public static void backgroundMedecin(LinearLayout layout, String s) {
        if(s.equals("patient"))
        {
            layout.setBackgroundResource(R.drawable.shape_incoming_bubble_medecin);
        }
        else
            layout.setBackgroundResource(R.drawable.shape_outgoing_bubbule_medecin);
        Log.e("messageTAG",s);

    }


    @BindingAdapter("visibilityMedecin")
    public static void setVisibleMedecin(ImageView layout, String s) {
        if(s.equals("patient"))
        {
            layout.setVisibility(View.GONE);
        }
        else
            layout.setVisibility(View.VISIBLE);
    }

    @BindingAdapter("visibilityNomMedecin")
    public static void setVisibleNomMedecin(TextView layout, String s) {
        if(s.equals("patient"))
        {
            layout.setVisibility(View.GONE);
        }
        else
            layout.setVisibility(View.VISIBLE);

    }


    @BindingAdapter("visibilityPatient")
    public static void setVisiblePatient(ImageView layout, String s) {
        if(!s.equals("patient"))
        {
            layout.setVisibility(View.GONE);
        }
        else
            layout.setVisibility(View.VISIBLE);
    }

    @BindingAdapter("visibilityNomPatient")
    public static void setVisibleNomPatient(TextView layout, String s) {
        if(!s.equals("patient"))
        {
            layout.setVisibility(View.GONE);
        }
        else
        {
            layout.setVisibility(View.VISIBLE);
            layout.setText(Constants.NOM_PATIENT);
        }

    }

    @BindingAdapter("imgVisibility")
    public static void imgVisibility(ImageView layout, String s) {
        if(s != null && !s.equals(""))
        {
            layout.setVisibility(View.VISIBLE);
            //RequestBuilder<Bitmap> requestBuilder = GlideApp.with(layout.getContext()).asBitmap();
            //requestBuilder.load(s).into(layout);
        }
        else
            layout.setVisibility(View.GONE);

    }

    @SuppressLint("ResourceAsColor")
    @BindingAdapter("txtVisibility")
    public static void txtVisibility(TextView layout, String s) {
        Context context = App.app.getApplicationContext();

        if(s.equals("Est ce que je peux utiliser ce médicament ?"))
        {
            layout.setText(R.string.demmand_medecine);
            layout.setTextColor(context.getResources().getColor(R.color.gris_chat));
        }
        else if(s.equals("Mon analyse"))
        {
            layout.setTextColor(context.getResources().getColor(R.color.gris_chat));
        }
        else
        {
            layout.setTextColor(context.getResources().getColor(R.color.white));
            layout.setVisibility(View.VISIBLE);
        }

    }


    @SuppressLint("ResourceAsColor")
    @BindingAdapter("txtColorChat")
    public static void txtColorChat(TextView layout, String s) {
        Context context = App.app.getApplicationContext();


        if(s.equals("patient"))
        {
            //layout.setText(R.string.demmand_medecine);
            layout.setTextColor(context.getResources().getColor(R.color.gris_chat));
        }
        else {
            layout.setTextColor(context.getResources().getColor(R.color.white));
            //layout.setVisibility(View.VISIBLE);
        }

    }



    @SuppressLint("ResourceAsColor")
    @BindingAdapter("txtVisibilityMedecin")
    public static void txtVisibilityMedecin(TextView layout, String s) {
        Context context = App.app.getApplicationContext();


        if(s.equals("Est ce que je peux utiliser ce médicament ?"))
        {
            layout.setText(R.string.demmand_medecine);
            layout.setTextColor(context.getResources().getColor(R.color.gris_chat));


        }
        else if(s.equals("Mon analyse"))
        {
            layout.setTextColor(context.getResources().getColor(R.color.gris_chat));
        }
        else
        {
            layout.setTextColor(context.getResources().getColor(R.color.white));
            layout.setVisibility(View.VISIBLE);
        }

    }


    @BindingAdapter("quizzVisibility")
    public static void quizzVisibility(ConstraintLayout layout, String s) {
        if(s.equals(""))
        {
            layout.setVisibility(View.GONE);
        }
        else
            layout.setVisibility(View.VISIBLE);

    }

    @SuppressLint("ResourceAsColor")
    @BindingAdapter("inrColor")
    public static void inrColor(TextView layout, double d) {
        Log.e("colorTAG",d+"");

        Context context = App.app.getApplicationContext();
        if(d<1.8)
        {
            layout.setTextColor(context.getResources().getColor(R.color.orange_drop));
        }
        else if(1.8<=d && d<=3)
        {
            layout.setTextColor(context.getResources().getColor(R.color.green_drop));
        }
        else if(d>3 && d<=5)
        {
            layout.setTextColor(context.getResources().getColor(R.color.orange_drop));
        }
        else if(d>5)
        {
            layout.setTextColor(context.getResources().getColor(R.color.red_drop));
        }

    }

    @SuppressLint("ResourceAsColor")
    @BindingAdapter("layoutColor")
    public static void layoutColor(LinearLayout layout, double d) {
        Log.e("colorTAG",d+"");

        Context context = App.app.getApplicationContext();
        if(d<1.8)
        {
            layout.setBackground(context.getResources().getDrawable(R.drawable.orange_shape));
        }
        else if(1.8<=d && d<=3)
        {
            layout.setBackground(context.getResources().getDrawable(R.drawable.green_shape));
        }
        else if(d>3 && d<=5)
        {
            layout.setBackground(context.getResources().getDrawable(R.drawable.orange_shape));
        }
        else if(d>5)
        {
            layout.setBackground(context.getResources().getDrawable(R.drawable.red_shape));
        }

    }

    @SuppressLint("ResourceAsColor")
    @BindingAdapter("ic_inr")
    public static void ic_inr(ImageView view, double d) {
        Log.e("colorTAG",d+"");

        Context context = App.app.getApplicationContext();
        if(d<1.8)
        {
            GlideApp.with(context).load(R.drawable.ic_inr).fitCenter().into(view);
        }
        else if(1.8<=d && d<=3)
        {
            GlideApp.with(context).load(R.drawable.ic_inr_2).fitCenter().into(view);
        }
        else if(d>3 && d<=5)
        {
            GlideApp.with(context).load(R.drawable.ic_inr).fitCenter().into(view);
        }
        else if(d>5)
        {
            GlideApp.with(context).load(R.drawable.ic_inr_1).fitCenter().into(view);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @BindingAdapter("dateChat")
    public static void dateChat(TextView layout, String s) {


        //Log.e("dateTag",s);
        Date dateMsg;
        try {
            String year = s.substring(0, 4);
            String month = s.substring(5, 7);
            String day = s.substring(8, 10);
            String hourMsg = s.substring(11, 16);

            layout.setText(year + "/" + month + "/" + day);

            Date date = new Date();


            String dayToday = (String) DateFormat.format("dd", date); // 20
            String monthToday = (String) DateFormat.format("MM", date); // 06
            String yearToday = (String) DateFormat.format("yyyy", date);

            //String minuteMsg          = (String) DateFormat.format("dd",   ); // 20


            if (day.equals(dayToday) && month.equals(monthToday) && year.equals(yearToday))
                layout.setText(hourMsg);
            else
                layout.setText(day + "/" + month + "/" + year + " " + hourMsg);
        }
        catch (StringIndexOutOfBoundsException e)
        {
            layout.setText(new Date().getHours()+":"+new Date().getMinutes());
        }


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @BindingAdapter("textChat")
    public static void textChat(TextView layout, String s) {
        Context context = App.app.getApplicationContext();

        if(s.equals(Constants.PATIENT))
            layout.setText(context.getString(R.string.sent_you_photo));
        else
            layout.setText(context.getString(R.string.your_sent_response));


    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    @BindingAdapter("nomPatientList")
    public static void nomPatientList(TextView layout, String s) {
                layout.setText(StringUtils.upperFirstChar(s));
        //layout.setText(id+"");
    }


    @BindingAdapter("nbrToTxt")
    public static void nbrToTxt(TextView view, int id) {
        if(id>0)
        view.setText(id+"");
    }


    @BindingAdapter("iconeNotif")
    public static void iconeNotif(ImageView view, int id) {
        //int s = Integer.parseInt(id);

        if(id==0)
            view.setVisibility(View.INVISIBLE);
        else
            view.setVisibility(View.VISIBLE);
    }


    @BindingAdapter("iconeMessage")
    public static void iconeMessage(ImageView view, String id) {
        //int s = Integer.parseInt(id);


        if(id.equals("true"))
        {
            Log.e("iconVisibilityTAG",id);
            view.setVisibility(View.VISIBLE);
        }
        else
        {
            Log.e("iconVisibilityTAG",id);
            view.setVisibility(View.INVISIBLE);
        }

    }

    @BindingAdapter("notifImg")
    public static void notifImg(ImageView view, String id) {
        //int s = Integer.parseInt(id);

        Context context = App.app.getApplicationContext();
        if(id.equals("Demande_Rdv"))
        {
            GlideApp.with(context).load(R.drawable.ic_rdv_1).fitCenter().into(view);
        }
        else
        {

            GlideApp.with(context).load(R.drawable.ic_inr_notif).fitCenter().into(view);
        }

    }

}
