package com.obabichev.technomessenger.network;

import java.net.Socket;

/**
 * Created by olegchuikin on 12/08/16.
 */

public interface SocketProvider {

    Socket getConnectionSocket();

}
