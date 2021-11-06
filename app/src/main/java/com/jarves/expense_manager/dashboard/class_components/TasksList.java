package com.jarves.expense_manager.dashboard.class_components;

import java.util.ArrayList;

public class TasksList {
    private ArrayList<Task> tasks;
    public TasksList(){
        tasks = new ArrayList<Task>();
    }
    public void addTask(Task task){
        tasks.add(task);
    }
    public ArrayList<Task> getTasks(){
        return tasks;
    }
    public void CreateSampleTasks(){
        Task task1 = new Task("Tution Fee",250,"Knowledge",new Date(12,5,2221), new Time(11,12));
        Task task2 = new Task("Gym Fee",2500,"Health",new Date(11,7,2021),new Time(13,2));
        Task task3 = new Task("Current Bill",500,"Needs",new Date(12,1,2221), new Time(11,12));
        Task task4 = new Task("Mobile Recharge",550,"Needs",new Date(11,2,2021), new Time(13,2));
        task4.setComplete(true);
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
    }
    public ArrayList<Task> getTasksCompleted(){
        ArrayList<Task> completed = new ArrayList<Task>();
        for(int i=0;i<tasks.size();i++){
            Task task = tasks.get(i);
            if(task.isComplete()){
                completed.add(task);
            }
        }
        return completed;

    }
    public ArrayList<Task> getTasksPending(){
        ArrayList<Task> pending = new ArrayList<Task>();
        for(int i=0;i<tasks.size();i++){
            Task task = tasks.get(i);
            if(!task.isComplete()){
                pending.add(task);
            }
        }
        return pending;
    }
}
