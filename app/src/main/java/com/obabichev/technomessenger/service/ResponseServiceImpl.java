package com.obabichev.technomessenger.service;

import android.util.Log;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.mapi.Response;
import com.obabichev.technomessenger.mapi.WelcomeMessage;
import com.obabichev.technomessenger.mapi.channel.ChannelListResponse;
import com.obabichev.technomessenger.mapi.channel.CreateChannelResponse;
import com.obabichev.technomessenger.mapi.enrollment.AuthResponse;
import com.obabichev.technomessenger.mapi.enrollment.RegisterResponse;
import com.obabichev.technomessenger.network.SocketProvider;
import com.obabichev.technomessenger.utils.JsonConverterUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import static com.obabichev.technomessenger.App.FILTER_TAG;

/**
 * Created by olegchuikin on 12/08/16.
 */
public class ResponseServiceImpl implements ResponseService {

    private ConnectableObservable<Response> messagesObservable;

    private SocketProvider socketProvider;

    private Map<Class, PublishSubject<Object>> publishSubjectMap;

    public ResponseServiceImpl(SocketProvider socketProvider) {
        this.socketProvider = socketProvider;

        publishSubjectMap = new HashMap<>();
        publishSubjectMap.put(WelcomeMessage.class, PublishSubject.create());
        publishSubjectMap.put(AuthResponse.class, PublishSubject.create());
        publishSubjectMap.put(RegisterResponse.class, PublishSubject.create());
        publishSubjectMap.put(ChannelListResponse.class, PublishSubject.create());
        publishSubjectMap.put(CreateChannelResponse.class, PublishSubject.create());
    }

    @Override
    public ConnectableObservable<Response> messagesObservable() {

        if (messagesObservable == null) {
            messagesObservable = createObservableForSocket();
        }
        return messagesObservable;
    }

    @Override
    public <T> PublishSubject<T> getSubjectForResponses(Class<T> tClass) {
        return (PublishSubject<T>) publishSubjectMap.get(tClass);
    }


    private ConnectableObservable<Response> createObservableForSocket() {

        return Observable
                .create(new Observable.OnSubscribe<Response>() {
                    @Override
                    public void call(Subscriber<? super Response> subscriber) {
                        try {
                            InputStream sin = socketProvider.getConnectionSocket().getInputStream();

                            byte[] data = new byte[32768];

                            while (true) {
                                int readedBytesCount = sin.read(data);

                                String json = bytesToString(data, readedBytesCount);
                                if (json.endsWith("}")) {
                                    Log.d(App.FILTER_TAG, "MESSAGE FROM SERVER: " + json);
                                    Response response = JsonConverterUtil.jsonToMessage(json);
                                    publishSubjectMap.get(response.getClass()).onNext(response);
                                    subscriber.onNext(response);
                                }
                            }
                        } catch (IOException e) {
                            //todo add something like subscriber.onError(e)
                            e.printStackTrace();
                            Log.d(FILTER_TAG, "ERROR while listening socket:" + e.getMessage());
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
}
