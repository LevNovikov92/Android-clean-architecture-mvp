package com.lev.mvpcleanarch.data.source.local;

import android.database.sqlite.SQLiteDatabase;

/**
 * Author: Lev
 * Date: 15.05.2017
 */

public interface DataBaseHelper {
    SQLiteDatabase getWritableDatabase();

    SQLiteDatabase getReadableDatabase();
}
