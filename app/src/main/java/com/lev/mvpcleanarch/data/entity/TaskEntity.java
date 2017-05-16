package com.lev.mvpcleanarch.data.entity;

import android.content.ContentValues;

import com.lev.mvpcleanarch.data.entity.mapper.Entity;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class TaskEntity implements Entity {

    public static final String CLOUD_PATH = "task/";
    public static final String TABLE = "task";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IS_COMPLETED = "isCompleted";

    private String id;
    private String title;
    private String description;
    private boolean isCompleted;

    public TaskEntity() {}

    public TaskEntity(ContentValues cv) {
        fromContentValues(cv);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void fromContentValues(ContentValues cv) {
        this.setId(cv.getAsString(COLUMN_ID));
        this.setTitle(cv.getAsString(COLUMN_TITLE));
        this.setDescription(cv.getAsString(COLUMN_DESCRIPTION));
        this.setCompleted(cv.getAsInteger(COLUMN_IS_COMPLETED) != 0);
    }

    public static String[] getColumns() {
        return new String[] { COLUMN_ID, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_IS_COMPLETED };
    }
}
