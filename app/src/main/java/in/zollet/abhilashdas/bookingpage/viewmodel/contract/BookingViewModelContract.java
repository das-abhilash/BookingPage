package in.zollet.abhilashdas.bookingpage.viewmodel.contract;


public interface BookingViewModelContract {


    interface View extends LifeCycle.View{
        void onBackPress();
        void onFinish();
    }

    interface ViewModel extends LifeCycle.ViewModel{
    }

}
