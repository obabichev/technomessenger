package com.obabichev.technomessenger.interactor;

import android.util.Log;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.model.Message;
import com.obabichev.technomessenger.network.SocketProvider;
import com.obabichev.technomessenger.utils.JsonConverterUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.obabichev.technomessenger.App.SOCKET_TAG;

/**
 * Created by olegchuikin on 12/08/16.
 */
public class SocketInteractorImpl implements SocketInteractor {

    private Observable<Message> messagesObservable;

    private SocketProvider socketProvider;

    public SocketInteractorImpl(SocketProvider socketProvider) {
        this.socketProvider = socketProvider;
    }

    @Override
    public Observable<Message> messagesObservable() {

        if (messagesObservable == null) {
            messagesObservable = createObservableForSocket();
        }
        return messagesObservable;
    }


    private Observable<Message> createObservableForSocket() {

        Log.d(App.SOCKET_TAG, "Creage observable for socket");

        return Observable
                .create(new Observable.OnSubscribe<Message>() {
                    @Override
                    public void call(Subscriber<? super Message> subscriber) {
                        try {
                            InputStream sin = socketProvider.getConnectionSocket().getInputStream();

                            try {
                                byte[] data = new byte[32768];
                                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                                while (true) {
                                    int readBytes = sin.read(data);
                                    if (readBytes != -1) {
                                        Log.d(App.SOCKET_TAG, "Something was received");
                                        outputStream.write(data, 0, readBytes);
                                        outputStream.flush();
                                        String result = outputStream.toString("utf-8");


                                        Pattern p = Pattern.compile("\\{[^\\{\\}]*\\{?[^\\{\\}]*\\}?[^\\{\\}]*\\}");
                                        Matcher m = p.matcher(result);

                                        while (m.find()) {
                                            Message message = JsonConverterUtil.jsonToMessage(m.group(0));
                                            subscriber.onNext(message);
                                        }

//                                Log.d(SOCKET_TAG, "Message from server: " + result);
                                    }
                                }

                            } catch (Exception e) {
                                Log.d(App.SOCKET_TAG, "ERROR: " + e.getMessage());
                                //todo do smth
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d(SOCKET_TAG, "ERROR:" + e.getMessage());
                        }

                        try {
                            socketProvider.getConnectionSocket().close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Log.d(App.SOCKET_TAG, "End of lestening");
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
