package com.obabichev.technomessenger.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.model.Message;
import com.obabichev.technomessenger.model.WelcomeMessage;
import com.obabichev.technomessenger.model.enrollment.AuthRequest;
import com.obabichev.technomessenger.model.enrollment.AuthResponse;
import com.obabichev.technomessenger.model.enrollment.RegisterResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by olegchuikin on 15/08/16.
 */
public class JsonConverterUtil {

    private final static Map<String, Class> actionsToMessageClass = createActionsToMessageClassMap();

    private final static String ACTION = "action";
    private final static String DATA = "data";

    public static Message jsonToMessage(String source) {
        Log.d(App.SOCKET_TAG, "Message from server: " + source);
        JsonParser parser = new JsonParser();

        JsonObject json = parser.parse(source).getAsJsonObject();

        /*switch(json.get("action").getAsString()){
            case "welcome":
                return new Gson().fromJson(json, WelcomeMessage.class);
            case "register":
                return new Gson().fromJson(json.get("data"), RegisterResponse.class);
        }*/

        if (json.get(ACTION).getAsString().equals("welcome")) {
            return new Gson().fromJson(json, WelcomeMessage.class);
        } else {
            Class clazz = actionsToMessageClass.get(json.get(ACTION).getAsString());
            JsonElement jdata = json.get(DATA);
            return (Message) new Gson().fromJson(jdata, clazz);
//            return (Message) new Gson().fromJson(json.get(DATA), actionsToMessageClass.get(json.get(ACTION)));
        }
    }

    public static String MessageToJson(Message message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", message.getAction());

        JsonElement data = new Gson().toJsonTree(message);
        jsonObject.add("data", data);
        return jsonObject.toString();
    }

    private static Map<String, Class> createActionsToMessageClassMap() {
        Map<String, Class> result = new HashMap<>();

        result.put("register", RegisterResponse.class);
        result.put("auth", AuthResponse.class);

        return result;
    }

}
