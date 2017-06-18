package in.zollet.abhilashdas.bookingpage.network.manager;

import android.support.v4.media.MediaBrowserCompat;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import in.zollet.abhilashdas.bookingpage.BookingApplication;
import in.zollet.abhilashdas.bookingpage.model.ServerResponse;
import in.zollet.abhilashdas.bookingpage.network.service.BookingService;
import in.zollet.abhilashdas.bookingpage.viewmodel.BaseViewModel;
import retrofit2.Response;
import rx.Observable;
import rx.functions.Action1;

public class BookingManager extends NetworkManager {

    @Inject
    BookingService bookingService;

    public BookingManager() {
        BookingApplication.getInstance().component().inject(this);
    }

    public Observable<ServerResponse> getSlotDetail(BaseViewModel.NetworkState networkState) {
        Map<String, String> data = new HashMap<>();
        data.put("username", "alok@x.coz");
        data.put("api_key", "a4aeb4e27f27b5786828f6cdf00d8d2cb44fe6d7");
        data.put("vc", "276");
        data.put("expert_username", "neha@healthifyme.com");
        data.put("primaryContact", "json");
        Observable<Response<ServerResponse>> bookingData = bookingService.getSlotDetail(data);
        return handleApiObservable(bookingData, networkState);
    }
}
