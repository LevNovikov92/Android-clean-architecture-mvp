package com.lev.mvpcleanarch.di.annotation;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Author: Lev
 * Date: 02.06.2017
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {}
