package com.lev.mvpcleanarch.data.entity;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class Task extends Entity {

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

    @Override
    public String getPath() {
        return "task";
    }

    @Override
    public String getTableName() {
        return "task";
    }
}
