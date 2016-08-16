package com.obabichev.technomessenger.interactor;

import android.util.Log;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.model.Message;
import com.obabichev.technomessenger.network.SocketProvider;
import com.obabichev.technomessenger.utils.JsonConverterUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

import static com.obabichev.technomessenger.App.SOCKET_TAG;

/**
 * Created by olegchuikin on 12/08/16.
 */
public class ResponseInteractorImpl implements ResponseInteractor {

    private ConnectableObservable<Message> messagesObservable;

    private final Pattern jsonPattern = Pattern.compile("\\{[^\\{\\}]*\\{?[^\\{\\}]*\\}?[^\\{\\}]*\\}");

    private SocketProvider socketProvider;

    public ResponseInteractorImpl(SocketProvider socketProvider) {
        this.socketProvider = socketProvider;
    }

    @Override
    public ConnectableObservable<Message> messagesObservable() {

        if (messagesObservable == null) {
            messagesObservable = createObservableForSocket();
        }
        return messagesObservable;
    }


    private ConnectableObservable<Message> createObservableForSocket() {

        return Observable
                .create(new Observable.OnSubscribe<Message>() {
                    @Override
                    public void call(Subscriber<? super Message> subscriber) {
                        try {
                            InputStream sin = socketProvider.getConnectionSocket().getInputStream();

                            byte[] data = new byte[32768];

                            while (true) {
                                int readedBytesCount = sin.read(data);

                                if (readedBytesCount != -1) {
                                    for (String json : splitJsons(bytesToString(data, readedBytesCount))) {
                                        Message message = JsonConverterUtil.jsonToMessage(json);
                                        Log.d(App.SOCKET_TAG, message.toString());
                                        Log.d(App.SOCKET_TAG, "Send message to subscriber");
                                        subscriber.onNext(message);
                                    }
                                }
                            }
                        } catch (IOException e) {
                            //todo add something like subscriber.onError(e)
                            e.printStackTrace();
                            Log.d(SOCKET_TAG, "ERROR while listening socket:" + e.getMessage());
                        }
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .publish();
    }

    private String bytesToString(byte[] data, int length) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(data, 0, length);
        outputStream.flush();
        return outputStream.toString("utf-8");
    }

    private List<String> splitJsons(String jsons){
        List<String> result = new ArrayList<>();

        Matcher m = jsonPattern.matcher(jsons);
        while (m.find()) {
            result.add(m.group(0));
        }
        return result;
    }

}
