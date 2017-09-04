package com.example.devsk.emergencyservice;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityDescription extends AppCompatActivity {

    private List<TaskModel> list;
    private Button buttn;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String key;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Task");
        buttn = (Button) findViewById(R.id.buttn);
        button = (Button) findViewById(R.id.button2);

        buttn.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        String name = intent.getStringExtra(TaskAdapter.EXTRA_M);
        String adress = intent.getStringExtra(TaskAdapter.EXTRA_A);
        String reason = intent.getStringExtra(TaskAdapter.EXTRA_R);
        key = intent.getStringExtra(TaskAdapter.EXTRA_P);

        buttn.setVisibility(View.INVISIBLE);
        reference.child(key).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        TaskModel task = dataSnapshot.getValue(TaskModel.class);
                        String str = task.dataTime;
                        if (str.equals("null")) {
                            buttn.setVisibility(View.VISIBLE);

                        }

                        button.setVisibility(View.INVISIBLE);
                        //           Log.d("fg", str);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


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

        reference.child("/" + key + "/dataTime").setValue(currentDataTime);
        toast.show();
        buttn.setVisibility(View.INVISIBLE);
        button.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onClick(View view) {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        String closeDataTime = sdf.format(new Date());
        Toast toast = Toast.makeText(ActivityDescription.this, closeDataTime, Toast.LENGTH_LONG);

        reference.child("/" + key + "/closeDataTime").setValue(closeDataTime);
        toast.show();
        button.setVisibility(View.INVISIBLE);


    }


}
