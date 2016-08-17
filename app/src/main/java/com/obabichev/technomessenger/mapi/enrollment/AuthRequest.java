package com.obabichev.technomessenger.mapi.enrollment;

import com.obabichev.technomessenger.mapi.Request;

/**
 * Created by olegchuikin on 15/08/16.
 */

public class AuthRequest extends Request {

    private String login;

    private String pass;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String getAction() {
        return "auth";
    }
}
