package com.rrdm.todo;

public class Todo {
    private int id;
    private String title;
    private String desc;
    private PRIORITY priority;
    private String status;
    private static int idCount = 1;

    public Todo() {
        title = "";
        desc = "";
        priority = PRIORITY.LOW;
        status = "todo";
        id = idCount++;
    }

    public Todo(String title, String desc, PRIORITY priority) {
        this.title = title;
        this.desc = desc;
        this.priority = priority;
        status = "todo";
        id = idCount++;
    }

    public Todo(int id, String title, String desc, String priority, String status) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.priority = PRIORITY.valueOf(priority);
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public PRIORITY getPriority() {
        return priority;
    }

    public void setPriority(PRIORITY priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
