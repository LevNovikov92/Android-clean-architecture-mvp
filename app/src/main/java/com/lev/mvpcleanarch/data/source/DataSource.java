package com.lev.mvpcleanarch.data.source;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public interface DataSource {

    <T> Observable<List<T>>  findAll(Class<T> clazz);
}
