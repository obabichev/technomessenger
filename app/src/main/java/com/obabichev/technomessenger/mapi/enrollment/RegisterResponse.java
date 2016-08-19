package com.obabichev.technomessenger.mapi.enrollment;

import com.obabichev.technomessenger.mapi.Response;

/**
 * Created by olegchuikin on 15/08/16.
 */

public class RegisterResponse extends Response {

    @Override
    public String getAction() {
        return "register";
    }

}
