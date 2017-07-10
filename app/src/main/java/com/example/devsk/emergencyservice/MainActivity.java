package com.example.devsk.emergencyservice;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.lang.Object.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends  AppCompatActivity{


    private RecyclerView recyclerView;
    private List<TaskModel> result;
    private TaskAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.task_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager  lin  = new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lin);


        adapter = new TaskAdapter(creatResult());
         recyclerView.setAdapter(adapter);




}
private   List<TaskModel> creatResult(){

List<TaskModel> list = new ArrayList<>();


   for (int i = 0; i < 5; i++){

       list.add(new TaskModel("Ленина 56", "кв 5", "Отключение электричества", "11"));
       list.add(new TaskModel("Ленина 57", "кв 5", "Отключение электричества", "11"));
       list.add(new TaskModel("Димитрова 27", "кв 5", "Отключение электричества", "11"));
       list.add(new TaskModel("Ленина 57", "кв 5", "Отключение электричества", "11"));
       list.add(new TaskModel("Комсомольский 69", "кв 238", "Отключение воды", "11"));
   }






    return  list;



}



}
