package com.lev.mvpcleanarch.data.entity;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class Task {

    public static final String CLOUD_PATH = "task/";
    public static final String TABLE = "task";

    private String title;
    private String description;
    private boolean isCompleted;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
