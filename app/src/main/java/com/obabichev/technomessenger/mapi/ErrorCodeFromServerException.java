package com.obabichev.technomessenger.mapi;

/**
 * Created by olegchuikin on 18/08/16.
 */

public class ErrorCodeFromServerException extends RuntimeException {

    int status;

    String error;

    public ErrorCodeFromServerException(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    @Override
    public String getMessage() {
        return String.format("%s(%d)", error, status);
    }

}
