package com.example.devsk.emergencyservice;




public class TaskModel  {

   public String name;
   public String adress;
    public String reason;
    public String key;
    public String dataTime;


    public TaskModel() {



    }

    public TaskModel(String name, String adress, String reason,String key,String dataTime) {
        this.name = name;
        this.adress = adress;
        this.reason = reason;
        this.key  = key;
        this.dataTime = dataTime;
    }





}
