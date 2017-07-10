package com.example.devsk.emergencyservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Intent intent = getIntent();
        String name = intent.getStringExtra(TaskAdapter.EXTRA_M);
        String adress = intent.getStringExtra(TaskAdapter.EXTRA_A);
        String reason =  intent.getStringExtra(TaskAdapter.EXTRA_R);

        TextView txtView = (TextView) findViewById(R.id.textView);
        txtView.setText(name);
        TextView adView = (TextView) findViewById(R.id.adress);
        adView.setText(adress);
        TextView resText = (TextView) findViewById(R.id.textViewReason);
        resText.setText(reason);

    }
}
