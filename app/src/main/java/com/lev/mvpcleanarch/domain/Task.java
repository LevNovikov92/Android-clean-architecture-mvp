package com.lev.mvpcleanarch.domain;

/**
 * Author: Lev
 * Date: 15.05.2017
 */

public class Task {

    private final String id;
    private String title;
    private String description;
    private boolean isCompleted;

    public Task(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

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
