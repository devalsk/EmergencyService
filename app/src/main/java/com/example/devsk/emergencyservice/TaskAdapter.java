package com.example.devsk.emergencyservice;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



import static android.support.v4.content.ContextCompat.startActivity;


public class TaskAdapter extends  RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {


    public static final String EXTRA_M = "EXTRA_M" ;
    public  static  final String EXTRA_A = "EXTRA_A";
    public static  final String EXTRA_R = "EXTRA_R";

    //   private Intent intent;
    private List<TaskModel> list;


    public TaskAdapter(List<TaskModel> list) {
        this.list = list;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, final int position)  {

        TaskModel taskm = list.get(position);


        holder.name.setText(taskm.name);
        holder.adress.setText(taskm.adress);
        holder.reason.setText(taskm.reason);



        holder.itemView.setOnClickListener(new OnClickListener() {

            TaskModel taskm = list.get(position);

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),ActivityDescription.class);
                String name = taskm.name;
                String adress = taskm.adress;
                String reason = taskm.reason;


                intent.putExtra(EXTRA_M,name);
                intent.putExtra(EXTRA_A,adress);
                intent.putExtra(EXTRA_R,reason);


                startActivity(view.getContext(),intent,null);
                //Toast.makeText(view.getContext(),taskm.name,Toast.LENGTH_SHORT).show();
            }
        });






    }

    @Override
    public int getItemCount() {
        return list.size();
    }






    class TaskViewHolder extends  RecyclerView.ViewHolder {

        TextView name,adress,reason;

        public TaskViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textName);
            adress = (TextView) itemView.findViewById(R.id.textAd);
            reason = (TextView) itemView.findViewById(R.id.textPr);
        }




    }


}
