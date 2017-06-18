package in.zollet.abhilashdas.bookingpage.dependencyinjection.module;

import dagger.Module;
import dagger.Provides;
import in.zollet.abhilashdas.bookingpage.viewmodel.implementation.BookingViewModel;


@Module
public class ViewModelModule {

    @Provides
    public BookingViewModel provideSignInViewModel() {
        return new BookingViewModel();
    }


}
