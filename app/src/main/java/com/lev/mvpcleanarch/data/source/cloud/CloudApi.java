package com.lev.mvpcleanarch.data.source.cloud;

import io.reactivex.Observable;

/**
 * Author: Lev
 * Date: 14.05.2017
 */

public interface CloudApi {

    Observable<String> jsonGet(String path);
}
