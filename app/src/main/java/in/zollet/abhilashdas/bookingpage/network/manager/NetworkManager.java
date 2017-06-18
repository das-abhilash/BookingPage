package in.zollet.abhilashdas.bookingpage.network.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.HttpRetryException;
import java.net.SocketTimeoutException;

import in.zollet.abhilashdas.bookingpage.BookingApplication;
import in.zollet.abhilashdas.bookingpage.dataprovider.exception.NoInternetException;
import in.zollet.abhilashdas.bookingpage.dataprovider.exception.ServerRuntimeException;
import in.zollet.abhilashdas.bookingpage.model.ServerResponse;
import in.zollet.abhilashdas.bookingpage.utility.Util;
import in.zollet.abhilashdas.bookingpage.viewmodel.BaseViewModel;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.AsyncOnSubscribe;
import rx.observables.SyncOnSubscribe;
import rx.schedulers.Schedulers;


public class NetworkManager {

    private int DEFAULT_RETRY_ATTEMPT = 3;

    protected Observable<ServerResponse> handleApiObservable(Observable<Response<ServerResponse>> t, final BaseViewModel.NetworkState networkState) {

        return t.doOnSubscribe(
                () -> {
                    if (!Util.isNetworkConnected()) {
                        throw new NoInternetException("Please check internet connection.");}

                    if (networkState != null)
                        networkState.setRequesting(true);
                })
                .doOnTerminate(() -> {
                    if (networkState != null)
                        networkState.setRequesting(true);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry((integer, throwable) -> integer<DEFAULT_RETRY_ATTEMPT && throwable instanceof SocketTimeoutException)
                .onErrorResumeNext(this::handleHttpError)
                .filter(responseServerResponse -> responseServerResponse.isSuccessful() && responseServerResponse.body() != null)
                .map(Response::body);
    }


    private Observable<Response<ServerResponse>> handleHttpError(Throwable throwable) throws RuntimeException {
        if (throwable instanceof HttpException) {
            int status = ((HttpRetryException) throwable).responseCode();
                throw new ServerRuntimeException("Server runtime exception" + "- status code - " + status);
        } else if (throwable instanceof NoInternetException) {
            throw new NoInternetException("Please check your internet connection.");

        } else {
            throw new NoInternetException("Something went wrong. Please try again");
        }
    }
}
