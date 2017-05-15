package com.lev.mvpcleanarch.exception;

/**
 * Author: Lev
 * Date: 15.05.2017
 */

public class ApiRequestException extends Exception {

    private int responseCode;

    public ApiRequestException(int responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
