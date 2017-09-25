package com.example.devsk.emergencyservice;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    public List<TaskModel> result;
    private TaskAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private TextView eptyItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eptyItem = (TextView) findViewById(R.id.tex_no_data);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Task");


        //  String key = reference.child("tt").push().getKey();
        // Log.d("fdd",key);


        result = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.task_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lin = new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lin);


        adapter = new TaskAdapter(result);
        recyclerView.setAdapter(adapter);

        updateList();
        isCheckEmpty();
        ;
    }

    private List<TaskModel> creatResult() {

        List<TaskModel> list = new ArrayList<>();


        for (int i = 0; i < 5; i++) {

            //   list.add(new TaskModel("Ленина 56", "кв 5", "Отключение электричества",""));

        }


        return list;


    }

    private void updateList() {

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                result.add(dataSnapshot.getValue(TaskModel.class));
                adapter.notifyDataSetChanged();
                isCheckEmpty();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                TaskModel model = dataSnapshot.getValue(TaskModel.class);

                int index = getItemIndex(model);
                result.set(index, model);
                adapter.notifyItemChanged(getItemIndex(model));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                TaskModel model = dataSnapshot.getValue(TaskModel.class);

                int index = getItemIndex(model);

                result.remove(index);
                adapter.notifyItemRemoved(index);
                isCheckEmpty();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private int getItemIndex(TaskModel model) {

        int index = -1;

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).key.equals(model.key)) {
                index = i;
                break;


            }


        }


        return index;
    }


    private void changeTask(int position) {
        TaskModel taskModel = result.get(position);
        taskModel.dataTime = "2019";

        Map<String, Object> taskValue = taskModel.toMap();
        Map<String, Object> newTask = new HashMap<>();

        newTask.put(taskModel.key, taskValue);
        reference.updateChildren(newTask);


    }

    private void removeTask(final int position) {

        reference.child(result.get(position).key).removeValue();

    }

    private void isCheckEmpty() {

        if (result.size() == 0) {

            recyclerView.setVisibility(View.INVISIBLE);
            eptyItem.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            eptyItem.setVisibility(View.INVISIBLE);

        }


    }


}
