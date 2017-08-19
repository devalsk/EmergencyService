package com.example.devsk.emergencyservice;


import java.util.HashMap;
import java.util.Map;


public class TaskModel {

    public String name;
    public String adress;
    public String reason;
    public String key;
    public String dataTime;


    public TaskModel() {


    }

    public TaskModel(String name, String adress, String reason, String key, String dataTime) {
        this.name = name;
        this.adress = adress;
        this.reason = reason;
        this.key = key;
        this.dataTime = dataTime;
    }

    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("adress", adress);
        result.put("reason", reason);
        result.put("key", key);
        result.put("dataTime", dataTime);

        return result;
    }


}
