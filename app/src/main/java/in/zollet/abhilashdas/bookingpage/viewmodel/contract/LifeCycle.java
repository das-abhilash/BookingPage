package in.zollet.abhilashdas.bookingpage.viewmodel.contract;

import android.content.Context;
import android.support.annotation.NonNull;

import in.zollet.abhilashdas.bookingpage.model.ErrorModel;

public interface LifeCycle {

    interface View{
        Context getActivityContext();
        void showProgress();
        void hideProgress();
        void showError(Exception e);
        void showError(Throwable t);
        void setErrorDrawable(ErrorModel errorModel);
    }

    interface ViewModel{
        void onViewResumed();
        void onViewStarted(@NonNull LifeCycle.View viewCallback);
        void onViewStopped();
        void onDestroy();
    }

}