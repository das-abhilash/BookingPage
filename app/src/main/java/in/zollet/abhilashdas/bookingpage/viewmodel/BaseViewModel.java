package in.zollet.abhilashdas.bookingpage.viewmodel;

import android.support.annotation.CallSuper;

import java.util.HashMap;
import java.util.Map;

import in.zollet.abhilashdas.bookingpage.model.ErrorModel;
import in.zollet.abhilashdas.bookingpage.utility.ErrorUtil;
import in.zollet.abhilashdas.bookingpage.viewmodel.contract.LifeCycle;
import in.zollet.abhilashdas.bookingpage.viewmodel.contract.RequestStates;
import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static in.zollet.abhilashdas.bookingpage.viewmodel.contract.RequestStates.REQUEST_FAILED;
import static in.zollet.abhilashdas.bookingpage.viewmodel.contract.RequestStates.REQUEST_NONE;
import static in.zollet.abhilashdas.bookingpage.viewmodel.contract.RequestStates.REQUEST_SUCCEEDED;


public abstract class BaseViewModel implements LifeCycle.ViewModel {

    private Map<String, NetworkState> networkStateMap = new HashMap<>();
    public CompositeSubscription compositeSubscription=new CompositeSubscription();

    public NetworkState getNetworkState(String tag) {

        NetworkState networkState = networkStateMap.get(tag);
        if (networkState != null) {
            return networkState;
        }
        NetworkState newNetworkState=new NetworkState();
        newNetworkState.setRequestState(REQUEST_NONE);
        networkStateMap.put(tag,newNetworkState);
        return newNetworkState;
    }


    @Override
    public void onDestroy() {
        if(compositeSubscription!=null && !compositeSubscription.isUnsubscribed()){
            compositeSubscription.unsubscribe();
        }
    }


    public abstract class MaybeNetworkObserver<T> implements Observer<T> {

        private String tag;


        public MaybeNetworkObserver(String tag) {
            this.tag = tag;
        }



        protected void setErrorRequestState(Throwable e) {
            NetworkState networkState=getNetworkState(tag);
            networkState.setLastError(e);
            networkState.setRequestState(REQUEST_FAILED);

        }

        protected void setSuccessRequestState(){
            NetworkState networkState=getNetworkState(tag);
            networkState.setRequestState(REQUEST_SUCCEEDED);
        }

        protected void setLastResponce(T responce){
            NetworkState networkState=getNetworkState(tag);
            networkState.setLastResponce(responce);
        }

        @CallSuper
        @Override
        public void onError(Throwable e) {
            setErrorRequestState(e);

        }

        @CallSuper
        @Override
        public void onCompleted() {
            setSuccessRequestState();

        }



        @CallSuper
        @Override
        public void onNext(T t) {
            setLastResponce(t);
        }
    }


    public class NetworkState {

        private boolean isRequesting;

        private
        @RequestStates.RequestState
        int requestState;

        protected Throwable lastError;

        protected Object lastResponce;

        public int getRequestState() {
            return requestState;
        }

        public Object getLastResponce() {
            return lastResponce;
        }

        public Throwable getLastError() {
            return lastError;
        }

        public void setRequestState(int requestState) {
            this.requestState = requestState;
        }

        public void setLastResponce(Object lastResponce) {
            this.lastResponce = lastResponce;
        }

        public void setLastError(Throwable lastError) {
            this.lastError = lastError;
        }

        public boolean isRequesting() {
            return isRequesting;
        }

        public void setRequesting(boolean requesting) {
            isRequesting = requesting;
        }
    }

    public Observable handleErrorObservable(Observable observable, final LifeCycle.View view, ErrorModel.ErrorActionListner listner, boolean... isButtonVisible){


        return observable.doOnSubscribe(() -> {
            if (view != null) {
                view.showProgress();
                view.setErrorDrawable(new ErrorModel(false));
            }
        })
                .doOnTerminate(() -> {
                    if(view!=null) view.hideProgress();})
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if(view!=null) {
                            view.showError(new Exception(throwable.getMessage()));
                            view.setErrorDrawable(ErrorUtil.getOnErrorErrorModel((Exception)
                                            throwable,view.getActivityContext(),listner,isButtonVisible));
                        }
                    }
                });
    }

    public Observable handleErrorObservable(Observable observable, final LifeCycle.View view){
        return handleErrorObservable(observable,view,null);
    }

}
