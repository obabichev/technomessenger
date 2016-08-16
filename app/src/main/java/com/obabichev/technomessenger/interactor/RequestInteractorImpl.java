package com.obabichev.technomessenger.interactor;

import android.util.Log;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.model.Message;
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
    public void sendMessage(Message message) {
        String json = JsonConverterUtil.MessageToJson(message);

        Log.d(App.SOCKET_TAG, "Send message: " + json);

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
        Log.d(App.SOCKET_TAG, "Message sended");
    }
}
