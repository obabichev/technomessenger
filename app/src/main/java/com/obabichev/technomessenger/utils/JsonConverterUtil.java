package com.obabichev.technomessenger.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.mapi.Request;
import com.obabichev.technomessenger.mapi.Response;
import com.obabichev.technomessenger.mapi.WelcomeMessage;
import com.obabichev.technomessenger.mapi.channel.ChannelListResponse;
import com.obabichev.technomessenger.mapi.channel.CreateChannelResponse;
import com.obabichev.technomessenger.mapi.enrollment.AuthResponse;
import com.obabichev.technomessenger.mapi.enrollment.RegisterResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by olegchuikin on 15/08/16.
 */
public class JsonConverterUtil {

    private final static Map<String, Class> actionsToMessageClass = createActionsToMessageClassMap();

    private final static String ACTION = "action";
    private final static String DATA = "data";

    public static Response jsonToMessage(String source) {
        Log.d(App.SOCKET_TAG, "DomainObject from server: " + source);
        JsonParser parser = new JsonParser();

        JsonObject json = parser.parse(source).getAsJsonObject();

        if (json.get(ACTION).getAsString().equals("welcome")) {
            return new Gson().fromJson(json, WelcomeMessage.class);
        } else {
            Class clazz = actionsToMessageClass.get(json.get(ACTION).getAsString());
            return (Response) new Gson().fromJson(json.get(DATA), clazz);
        }
    }

    public static String MessageToJson(Request request) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("action", request.getAction());

        JsonElement data = new Gson().toJsonTree(request);
        jsonObject.add("data", data);
        return jsonObject.toString();
    }

    private static Map<String, Class> createActionsToMessageClassMap() {
        Map<String, Class> result = new HashMap<>();

        result.put("register", RegisterResponse.class);
        result.put("auth", AuthResponse.class);
        result.put("channellist", ChannelListResponse.class);
        result.put("createchannel", CreateChannelResponse.class);

        return result;
    }

}
