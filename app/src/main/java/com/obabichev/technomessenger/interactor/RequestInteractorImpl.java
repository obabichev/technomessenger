package com.obabichev.technomessenger.interactor;

import android.util.Log;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.mapi.Request;
import com.obabichev.technomessenger.network.SocketProvider;
import com.obabichev.technomessenger.utils.JsonConverterUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Created by olegchuikin on 15/08/16.
 */
public class RequestInteractorImpl implements RequestInteractor {

    private SocketProvider socketProvider;


    public RequestInteractorImpl(SocketProvider socketProvider) {
        this.socketProvider = socketProvider;
    }

    @Override
    public void sendMessage(Request request) {
        String json = JsonConverterUtil.MessageToJson(request);

        Log.d(App.SOCKET_TAG, "Send request: " + json);

        try {
            sendJson(json);
        } catch (IOException e) {
            //todo we are not in the perfect world
            e.printStackTrace();
        }
    }

    private void sendJson(String json) throws IOException {
        OutputStream out = socketProvider.getConnectionSocket().getOutputStream();

        out.write(json.getBytes(Charset.forName("UTF-8")));
        out.flush();
        Log.d(App.SOCKET_TAG, "DomainObject sended");
    }
}
