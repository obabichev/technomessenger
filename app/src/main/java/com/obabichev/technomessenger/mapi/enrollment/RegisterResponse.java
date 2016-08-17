package com.obabichev.technomessenger.mapi.enrollment;

import com.obabichev.technomessenger.mapi.Response;

/**
 * Created by olegchuikin on 15/08/16.
 */

public class RegisterResponse extends Response {

    int status;

    String error;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String getAction() {
        return "register";
    }
}
