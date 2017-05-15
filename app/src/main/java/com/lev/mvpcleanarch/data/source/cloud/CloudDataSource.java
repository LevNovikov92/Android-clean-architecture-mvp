package com.lev.mvpcleanarch.data.source.cloud;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lev.mvpcleanarch.data.source.DataSource;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public class CloudDataSource implements DataSource {


    @Override
    public <T> Observable<List<T>> findAll(Class<T> clazz) {
        return Observable.create(emitter -> {
            try {
                final T instance = clazz.newInstance();
                final Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<T>>(){}.getType();
                T[] tasks = gson.fromJson("aasd", collectionType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
