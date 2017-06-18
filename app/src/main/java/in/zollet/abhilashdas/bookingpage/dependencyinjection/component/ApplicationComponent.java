package in.zollet.abhilashdas.bookingpage.dependencyinjection.component;



import javax.inject.Singleton;

import dagger.Component;
import in.zollet.abhilashdas.bookingpage.BookingApplication;
import in.zollet.abhilashdas.bookingpage.dependencyinjection.module.AppModule;
import in.zollet.abhilashdas.bookingpage.dependencyinjection.module.NetworkModule;
import in.zollet.abhilashdas.bookingpage.network.manager.BookingManager;
import in.zollet.abhilashdas.bookingpage.view.fragment.BookingFragment;
import in.zollet.abhilashdas.bookingpage.viewmodel.implementation.BookingViewModel;


@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface ApplicationComponent {


    void inject(BookingViewModel bookingViewModel);

    void inject(BookingFragment bookingFragment);

    void inject(BookingApplication bookingApplication);

    void inject(BookingManager bookingManager);
}
