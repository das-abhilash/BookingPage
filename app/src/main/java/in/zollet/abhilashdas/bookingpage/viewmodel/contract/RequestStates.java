package in.zollet.abhilashdas.bookingpage.viewmodel.contract;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RequestStates {

    @IntDef({
            REQUEST_NONE,
            REQUEST_RUNNING,
            REQUEST_SUCCEEDED,
            REQUEST_FAILED
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface RequestState{}

    public static final int REQUEST_NONE = 0;
    public static final int REQUEST_RUNNING = 1;
    public static final int REQUEST_SUCCEEDED = 2;
    public static final int REQUEST_FAILED = 3;
}