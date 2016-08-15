package com.obabichev.technomessenger.interactor;

import android.util.Log;

import com.google.gson.Gson;
import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.model.Message;
import com.obabichev.technomessenger.model.WelcomeMessage;
import com.obabichev.technomessenger.network.SocketProvider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static com.obabichev.technomessenger.App.SOCKET_TAG;

/**
 * Created by olegchuikin on 12/08/16.
 */
public class SocketInteractorImpl implements SocketInteractor {

    private Observable<Message> messagesObservable;

    @Inject
    SocketProvider socketProvider;

    public SocketInteractorImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<Message> messagesObservable() {

        if (messagesObservable == null) {
            messagesObservable = createObservableForSocket();
        }
        return messagesObservable;
    }


    private Observable<Message> createObservableForSocket() {

        return Observable.create(new Observable.OnSubscribe<Message>() {
            @Override
            public void call(Subscriber<? super Message> subscriber) {
                try {
                    Socket socket = socketProvider.getConnectionSocket();

                    InputStream sin = socket.getInputStream();

                    try {
                        byte[] data = new byte[32768];
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                        while(true) {
                            int readBytes = sin.read(data);
                            if (readBytes != -1) {
                                outputStream.write(data, 0, readBytes);
                                outputStream.flush();
                                String result = outputStream.toString("utf-8");

                                Log.d(SOCKET_TAG, result);

                                WelcomeMessage message = new Gson().fromJson(result, WelcomeMessage.class);

                                /*try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e){

                                }*/
                                subscriber.onNext(message);
                            }
                        }

                    } catch (Exception e) {
                        //todo do smth
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(SOCKET_TAG, "ERROR:" + e.getMessage());
                }
            }
        }).subscribeOn(Schedulers.io());
    }

}
