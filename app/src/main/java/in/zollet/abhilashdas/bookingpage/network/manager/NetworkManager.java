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
import in.zollet.abhilashdas.bookingpage.viewmodel.BaseViewModel;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;


public class NetworkManager {

    private int DEFAULT_RETRY_ATTEMPT = 3;

    protected <T> Observable<ServerResponse> handleApiObservable(Observable<Response<ServerResponse>> t, final BaseViewModel.NetworkState networkState) {

        return t.doOnSubscribe(

                new Action0() {
                    @Override
                    public void call() {
                        if (!isNetworkConnected()) {
                            throw new NoInternetException("Please check internet connection.");
                        }

                        if (networkState != null)
                            networkState.setRequesting(true);
                    }

                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (networkState != null)
                            networkState.setRequesting(true);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // API.URL
                .retry(new Func2<Integer, Throwable, Boolean>() {
                    @Override
                    public Boolean call(Integer integer, Throwable throwable) {
                        return integer<DEFAULT_RETRY_ATTEMPT && throwable instanceof SocketTimeoutException;
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Response<ServerResponse>>>() {
                    @Override
                    public Observable<? extends Response<ServerResponse>> call(Throwable throwable) {
                        return handleHttpError(throwable);
                    }
                })
                .filter(new Func1<Response<ServerResponse>, Boolean>() {
                    @Override
                    public Boolean call(Response<ServerResponse> responseServerResponse) {
                        return responseServerResponse.isSuccessful() && responseServerResponse.body() != null;
                    }
                })
                .map(new Func1<Response<ServerResponse>, ServerResponse>() {
                    @Override
                    public ServerResponse call(Response<ServerResponse> serverResponseResponse) {
                        return serverResponseResponse.body();
                    }
                });
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) BookingApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private <T> Observable<Response<ServerResponse>> handleHttpError(Throwable throwable) throws RuntimeException {
        if (throwable instanceof HttpException) {
            int status = ((HttpRetryException) throwable).responseCode();
                throw new ServerRuntimeException("Server runtime exception" + "- status code - " + status);
        } else if (throwable instanceof NoInternetException) {
            throw new NoInternetException("Please check your internet connection." /*+ throwable.toString()*/);

        } else {
            throw new NoInternetException("Something went wrong. Please try again");
        }
    }
}
