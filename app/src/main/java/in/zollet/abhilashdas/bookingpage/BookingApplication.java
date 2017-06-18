package in.zollet.abhilashdas.bookingpage;

import android.app.Application;
import android.content.Context;

import in.zollet.abhilashdas.bookingpage.dependencyinjection.component.ApplicationComponent;
import in.zollet.abhilashdas.bookingpage.dependencyinjection.component.DaggerApplicationComponent;
import in.zollet.abhilashdas.bookingpage.dependencyinjection.module.AppModule;

public class BookingApplication extends Application {

    private ApplicationComponent component;
    private static BookingApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        component = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component().inject(this);


    }

    public static BookingApplication get(Context context) {
        return (BookingApplication) context.getApplicationContext();
    }

    public static BookingApplication getInstance() {
        return application;
    }


    public ApplicationComponent component() {
        return component;
    }

}
