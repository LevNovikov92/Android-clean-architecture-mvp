package com.lev.mvpcleanarch.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lev.mvpcleanarch.data.entity.TaskEntity;

/**
 * Author: Lev
 * Date: 15.05.2017
 */

public class DbOpenHelper extends SQLiteOpenHelper implements DataBaseHelper {

    private static final String DB_NAME = "database.db";
    private static final int    DB_VERSION = 1;

    private static final String CREATE_TASK_TABLE = String.format("CREATE TABLE `%s` (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s TEXT, " +
            "%s TEXT, " +
            "%s INTEGER DEFAULT 0)", TaskEntity.TABLE, TaskEntity.COLUMN_ID, TaskEntity.COLUMN_TITLE,
            TaskEntity.COLUMN_DESCRIPTION, TaskEntity.COLUMN_IS_COMPLETED);

    private static final String[] TEST_DATA = new String[]{
            String.format("INSERT INTO `%s` (%s, %s, %s) VALUES ('title1', 'desc1', 1)",
                    TaskEntity.TABLE, TaskEntity.COLUMN_TITLE,
                    TaskEntity.COLUMN_DESCRIPTION, TaskEntity.COLUMN_IS_COMPLETED),
            String.format("INSERT INTO `%s` (%s, %s, %s) VALUES ('title2', 'desc2', 0)",
                    TaskEntity.TABLE, TaskEntity.COLUMN_TITLE,
                    TaskEntity.COLUMN_DESCRIPTION, TaskEntity.COLUMN_IS_COMPLETED),
            String.format("INSERT INTO `%s` (%s, %s, %s) VALUES ('title3', 'desc3', 0)",
                    TaskEntity.TABLE, TaskEntity.COLUMN_TITLE,
                    TaskEntity.COLUMN_DESCRIPTION, TaskEntity.COLUMN_IS_COMPLETED),
    };

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TASK_TABLE);
        for (String data : TEST_DATA)
            sqLiteDatabase.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //nothing
    }
}
