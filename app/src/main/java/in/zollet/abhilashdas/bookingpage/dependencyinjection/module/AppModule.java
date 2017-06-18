package in.zollet.abhilashdas.bookingpage.dependencyinjection.module;

import android.content.Context;
import android.location.LocationManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.zollet.abhilashdas.bookingpage.BookingApplication;
import in.zollet.abhilashdas.bookingpage.dependencyinjection.qualifier.ForApplication;
import in.zollet.abhilashdas.bookingpage.network.manager.BookingManager;

import static android.content.Context.LOCATION_SERVICE;

@Module(includes = {ViewModelModule.class})
public class AppModule {

    private final BookingApplication application;

    public AppModule(BookingApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) application.getSystemService(LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    BookingManager provideProfileManager() {
        return new BookingManager();
    }


}
