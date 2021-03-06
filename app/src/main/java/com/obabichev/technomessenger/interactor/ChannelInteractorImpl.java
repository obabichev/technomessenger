package com.obabichev.technomessenger.interactor;

import com.obabichev.technomessenger.App;
import com.obabichev.technomessenger.mapi.ErrorCodeFromServerException;
import com.obabichev.technomessenger.mapi.Response;
import com.obabichev.technomessenger.mapi.ResponseCodes;
import com.obabichev.technomessenger.mapi.channel.ChannelListRequest;
import com.obabichev.technomessenger.mapi.channel.ChannelListResponse;
import com.obabichev.technomessenger.mapi.channel.CreateChannelRequest;
import com.obabichev.technomessenger.mapi.channel.CreateChannelResponse;
import com.obabichev.technomessenger.model.Channel;
import com.obabichev.technomessenger.repository.UserRepository;
import com.obabichev.technomessenger.service.RequestService;
import com.obabichev.technomessenger.service.ResponseService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import static com.obabichev.technomessenger.mapi.ResponseCodes.ErrOK;

/**
 * Created by olegchuikin on 18/08/16.
 */
public class ChannelInteractorImpl implements ChannelInteractor {

    @Inject
    RequestService requestService;

    @Inject
    ResponseService responseService;

    @Inject
    UserRepository userRepository;

    private List<Channel> channels;

    ChannelInteractorImpl() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<List<Channel>> getChannels() {

        if (channels != null) {
            return Observable.just(channels);
        }

        Observable<List<Channel>> result = responseService.messagesObservable().filter(new Func1<Response, Boolean>() {
            @Override
            public Boolean call(Response response) {
                return response instanceof ChannelListResponse;
            }
        }).flatMap(new Func1<Response, Observable<List<Channel>>>() {
            @Override
            public Observable<List<Channel>> call(Response response) {

                if (response.getStatus() != ErrOK) {
                    return Observable.error(new ErrorCodeFromServerException(response.getStatus(), response.getError()));
                }

                return Observable.just(((ChannelListResponse) response).getChannels());
            }
        });

        ChannelListRequest request = new ChannelListRequest();
        request.setCid(userRepository.getUserId());
        request.setSid(App.sid);
        requestService.sendMessage(request);

        return result;
    }

    @Override
    public Observable<Channel> createChannel(String name, String description) {

        final Channel channel = new Channel();
        channel.setName(name);
        channel.setDescr(description);

        Observable<Channel> result = responseService.getSubjectForResponses(CreateChannelResponse.class)
                .flatMap(new Func1<CreateChannelResponse, Observable<Channel>>() {
                    @Override
                    public Observable<Channel> call(CreateChannelResponse response) {
                        if (response.getStatus() != ErrOK) {
                            return Observable.error(new ErrorCodeFromServerException(response.getStatus(), response.getError()));
                        }
                        channel.setChid(response.getChid());
                        return Observable.just(channel);
                    }
                });

        CreateChannelRequest request = new CreateChannelRequest();
        request.setCid(userRepository.getUserId());
        request.setDescr(description);
        request.setName(name);
        request.setSid(App.sid);
        requestService.sendMessage(request);

        return result;
    }
}
