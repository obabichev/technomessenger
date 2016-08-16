package com.obabichev.technomessenger.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.model.Message;
import com.obabichev.technomessenger.model.WelcomeMessage;
import com.obabichev.technomessenger.model.enrollment.RegisterResponse;

/**
 * Created by olegchuikin on 15/08/16.
 */
public class JsonConverterUtil {

    public static Message jsonToMessage(String source){
        Log.d(App.SOCKET_TAG, "Message from server: " + source);
        JsonParser parser = new JsonParser();

        JsonObject json = parser.parse(source).getAsJsonObject();

        switch(json.get("action").getAsString()){
            case "welcome":
                return new Gson().fromJson(json, WelcomeMessage.class);
            case "register":
                return new Gson().fromJson(json.get("data"), RegisterResponse.class);
        }
        return null;
    }

    public static String MessageToJson(Message message){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", message.getAction());

        JsonElement data = new Gson().toJsonTree(message);
        jsonObject.add("data", data);
        return jsonObject.toString();
    }

}
