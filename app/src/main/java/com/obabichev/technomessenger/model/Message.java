package com.obabichev.technomessenger.model;

import java.lang.reflect.Field;

/**
 * Created by olegchuikin on 12/08/16.
 */

public class Message {

    @Override
    public String toString() {
        //todo delete in release!!
        StringBuilder sb = new StringBuilder();

        try {
            sb.append("Class:").append(this.getClass().getSimpleName()).append("{");
            Field[] declaredFields = this.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                sb.append(declaredField.getName()).append(":")
                        .append(declaredField.get(this)).append(", ");
            }
            sb.append("}");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
