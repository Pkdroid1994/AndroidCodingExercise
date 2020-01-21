package com.prashant.androidcodingexercise.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.prashant.androidcodingexercise.R;
import com.prashant.androidcodingexercise.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Setting Up Toolbar
        setSupportActionBar(activityMainBinding.toolbarInclude.toolbarCommon);
        getSupportActionBar().setTitle("Fetching Data");

        //Setting Up Facts Fragment To Show Facts List
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new FactsFragment()).commit();

    }
}
