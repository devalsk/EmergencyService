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

    private  FirebaseDatabase database;
    private  DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Task");

      reference.child("/task/dataTime").setValue("Время 2017");

        result = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.task_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager  lin  = new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lin);

        updateList();

       adapter = new TaskAdapter(result);
          recyclerView.setAdapter(adapter);




}
    private   List<TaskModel> creatResult(){

List<TaskModel> list = new ArrayList<>();


   for (int i = 0; i < 5; i++){

    //   list.add(new TaskModel("Ленина 56", "кв 5", "Отключение электричества",""));

   }






    return  list;



}

private  void updateList(){

    reference.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            result.add(dataSnapshot.getValue(TaskModel.class));
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            TaskModel model = dataSnapshot.getValue(TaskModel.class);

            int index = getItemIndex(model);

            result.set(index,model);
            adapter.notifyItemChanged(getItemIndex(model));
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            TaskModel model = dataSnapshot.getValue(TaskModel.class);

            int index = getItemIndex(model);

            result.remove(index);
            adapter.notifyItemRemoved(index);

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


}
