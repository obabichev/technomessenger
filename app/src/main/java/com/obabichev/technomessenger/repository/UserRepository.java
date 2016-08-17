package com.obabichev.technomessenger.repository;

/**
 * Created by olegchuikin on 17/08/16.
 */

public interface UserRepository {

    String getUserId();

    String getUserPassword();

    void setUserId(String userId);

    void setUserPassword(String password);


    void fixateUserCredentionals();

    void extractUserCredentionals();

    void clearUserCredentionals();

}
