package com.obabichev.technomessenger.network;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;

import static com.obabichev.technomessenger.App.SOCKET_TAG;

/**
 * Created by olegchuikin on 12/08/16.
 */
public class SocketProviderImpl implements SocketProvider {

    private Socket socket;

    @Override
    public Socket getConnectionSocket() {
        if (socket == null || socket.isClosed()){
            socket = connect();
        }
        return socket;
    }

    private Socket connect(){
        Log.d(SOCKET_TAG, "CONNECT");
        try {
            return new Socket("10.22.112.245", 7777);
        } catch (IOException e) {
            //todo we are not in the perfect world
            Log.d(SOCKET_TAG, "ERROR: failed connection");
            e.printStackTrace();
        }
        return null;
    }
}
