package com.seemantov.pokmy.pokmy.demands;

import android.os.Bundle;

import com.seemantov.pokmy.R;
import com.seemantov.pokmy.base.BaseActivity;
import com.seemantov.pokmy.base.BaseFragment;
import com.seemantov.pokmy.utils.Logger;

public class DemandsActivity extends BaseActivity {

    public static int fg=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission);

        String f;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                f="0";
            } else {
                f= extras.getString("f");
            }
        } else {
            f= "2";
        }

        Logger.e("f=",f);

        BaseFragment mission1Fragment = new Demand1Fragment();
        BaseFragment mission2Fragment = new Demand2Fragment();
        BaseFragment mission3Fragment = new Demand3Fragment();

        if(f.equals("0"))
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mission1Fragment).disallowAddToBackStack().commit();
        else if(f.equals("1"))
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mission2Fragment).disallowAddToBackStack().commit();
        else if(f.equals("2"))
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mission3Fragment).disallowAddToBackStack().commit();

    }
}
