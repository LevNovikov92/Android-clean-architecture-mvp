package com.lev.mvpcleanarch.data.source.local;

import android.database.sqlite.SQLiteDatabase;

import com.lev.mvpcleanarch.BuildConfig;
import com.lev.mvpcleanarch.data.entity.TaskEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static android.database.sqlite.SQLiteDatabase.OPEN_READWRITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Author: Lev
 * Date: 18.05.2017
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LocalDataSourceTest {

    private static final String DB_PATH = "/database/taskTestDb.db";
    private DataBaseHelper mHelper;

    @Before
    public void setUp() throws Exception {
        String path = getClass().getResource(DB_PATH).toURI().getPath();
        File dbFile = new File(path);
        assertThat(dbFile.exists()).isTrue();
        final String dbPath = dbFile.getAbsolutePath();

        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, OPEN_READWRITE);
        mHelper = mock(DataBaseHelper.class);
        when(mHelper.getReadableDatabase()).thenReturn(db);
    }

    @Test
    public void getTasks() throws Exception {
        final LocalDataSource source = new LocalDataSource(mHelper);
        final TestObserver<List<TaskEntity>> observer = source.getTasks().test();
        observer.assertComplete();
        observer.assertValueCount(1);
        final List<TaskEntity> tasks = observer.values().get(0);
        assertEquals(3, tasks.size());
        assertEquals("1", tasks.get(0).getId());
        assertEquals(true, tasks.get(0).isCompleted());
        assertEquals("title2", tasks.get(1).getTitle());
        assertEquals("desc3", tasks.get(2).getDescription());
        assertEquals(false, tasks.get(2).isCompleted());
    }
}