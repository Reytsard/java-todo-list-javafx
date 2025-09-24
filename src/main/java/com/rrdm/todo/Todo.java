package com.rrdm.todo;

public class Todo {
    private String title;
    private String desc;
    private PRIORITY priority;
    private String status;

    public Todo(){
        title = "";
        desc = "";
        priority = PRIORITY.LOW;
        status = "todo";
    }

    public Todo(String title, String desc, PRIORITY priority) {
        this.title = title;
        this.desc = desc;
        this.priority = priority;
        status = "todo";
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
