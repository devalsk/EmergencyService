package com.example.devsk.emergencyservice;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Date;

public class ActivityDescription extends AppCompatActivity {

    private Button buttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        buttn = (Button) findViewById(R.id.buttn);

        buttn.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String name = intent.getStringExtra(TaskAdapter.EXTRA_M);
        String adress = intent.getStringExtra(TaskAdapter.EXTRA_A);
        String reason = intent.getStringExtra(TaskAdapter.EXTRA_R);

        TextView txtView = (TextView) findViewById(R.id.textView);
        txtView.setText(name);
        TextView adView = (TextView) findViewById(R.id.adress);
        adView.setText(adress);
        TextView resText = (TextView) findViewById(R.id.textViewReason);
        resText.setText(reason);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClic(View view) {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        String currentDataTime = sdf.format(new Date());
        Toast toast = Toast.makeText(ActivityDescription.this, currentDataTime, Toast.LENGTH_LONG);
        toast.show();

    }
}
