package com.jarves.expense_manager.class_components;

public class Task {
    String name;
    int amount;
    Date date;
    Time time;
    String category;
    boolean isComplete = false;

    public Task(String name, int amount,String category, Date date, Time time) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return String.valueOf(amount);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", time=" + time +
                ", category='" + category + '\'' +
                ", isComplete=" + isComplete +
                '}';
    }
}
