package com.lev.mvpcleanarch.data.repositories;

import com.lev.mvpcleanarch.data.entity.TaskEntity;
import com.lev.mvpcleanarch.data.source.DataSourceFactory;
import com.lev.mvpcleanarch.domain.Task;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static com.lev.mvpcleanarch.internal.utils.TestUtils.getTaskEntity;
import static junit.framework.Assert.assertEquals;

/**
 * Author: Lev
 * Date: 18.05.2017
 */
public class TaskDataRepositoryTest {
    @Test
    public void getTasks() throws Exception {
        final DataSourceFactory factory = Mockito.mock(DataSourceFactory.class);
        Mockito.when(factory.getDataSource()).thenReturn(() -> {
            final List<TaskEntity> tasks = new ArrayList<>();
            tasks.add(getTaskEntity("1", "title1", "desc1", true));
            tasks.add(getTaskEntity("2", "title2", "desc2", true));
            return Observable.just(tasks);
        });
        final TaskDataRepository repository = new TaskDataRepository(factory);
        final TestObserver<List<Task>> observer = repository.getTasks().test();
        observer.assertComplete();
        observer.assertValueCount(1);

        final List<Task> tasks = observer.values().get(0);
        assertEquals("1", tasks.get(0).getId());
        assertEquals("title2", tasks.get(1).getTitle());
    }
}