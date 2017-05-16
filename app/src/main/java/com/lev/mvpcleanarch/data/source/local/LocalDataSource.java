package com.lev.mvpcleanarch.data.source.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.VisibleForTesting;

import com.lev.mvpcleanarch.data.entity.TaskEntity;
import com.lev.mvpcleanarch.data.source.DataSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.internal.Util;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class LocalDataSource implements DataSource {

    @SuppressWarnings({"WeakerAccess", "CanBeFinal"})
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    @Inject
    public DataBaseHelper dbHelper;

    @Override
    public Observable<List<TaskEntity>> getTasks() {
        return Observable.create(emitter -> {
            Cursor cursor = null;
            SQLiteDatabase db = null;
            try {
                db = dbHelper.getReadableDatabase();
                cursor = db.query(TaskEntity.TABLE, TaskEntity.getColumns(), null, null, null, null, null);
                final List<TaskEntity> tasks = new ArrayList<>();
                while (cursor.moveToNext()) {
                    final ContentValues cv = new ContentValues();
                    DatabaseUtils.cursorRowToContentValues(cursor, cv);
                    tasks.add(new TaskEntity(cv));
                }
                emitter.onNext(tasks);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
                e.printStackTrace();
            } finally {
                Util.closeQuietly(cursor);
                Util.closeQuietly(db);
            }
        });
    }
}
