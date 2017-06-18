package in.zollet.abhilashdas.bookingpage.network.service;

import java.util.Map;

import in.zollet.abhilashdas.bookingpage.model.ServerResponse;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface BookingService {

    @GET("api/v1/booking/slots/all")
    Observable<Response<ServerResponse>> getSlotDetail( @QueryMap Map<String, String> data);
}
