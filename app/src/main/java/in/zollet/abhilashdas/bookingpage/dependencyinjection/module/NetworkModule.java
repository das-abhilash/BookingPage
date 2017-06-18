package in.zollet.abhilashdas.bookingpage.dependencyinjection.module;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.zollet.abhilashdas.bookingpage.BuildConfig;
import in.zollet.abhilashdas.bookingpage.network.service.BookingService;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.schedulers.Schedulers;


@Module
public class NetworkModule {




    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        return client.build();
    }


    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://108.healthifyme.com/")
                .client( okHttpClient)
                .build();
        return retrofit;
    }


    @Provides
    BookingService provideBookingApiService(Retrofit retrofit) {
        return retrofit.create(BookingService.class);
    }

}
