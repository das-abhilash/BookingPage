package in.zollet.abhilashdas.bookingpage.viewmodel.implementation;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import in.zollet.abhilashdas.bookingpage.BookingApplication;
import in.zollet.abhilashdas.bookingpage.R;
import in.zollet.abhilashdas.bookingpage.configuration.ViewPagerConfiguration;
import in.zollet.abhilashdas.bookingpage.model.ErrorModel;
import in.zollet.abhilashdas.bookingpage.model.QuaterModel;
import in.zollet.abhilashdas.bookingpage.model.ServerResponse;
import in.zollet.abhilashdas.bookingpage.model.SlotDetail;
import in.zollet.abhilashdas.bookingpage.network.manager.BookingManager;
import in.zollet.abhilashdas.bookingpage.utility.Util;
import in.zollet.abhilashdas.bookingpage.view.adapter.ExapndableAdapter;
import in.zollet.abhilashdas.bookingpage.view.adapter.viewpageradapter.LayoutProvider;
import in.zollet.abhilashdas.bookingpage.view.adapter.viewpageradapter.ViewPagerList;
import in.zollet.abhilashdas.bookingpage.view.adapter.viewpageradapter.ViewPagerMap;
import in.zollet.abhilashdas.bookingpage.view.viewholder.RecylerViewHolder;
import in.zollet.abhilashdas.bookingpage.viewmodel.BaseViewModel;
import in.zollet.abhilashdas.bookingpage.viewmodel.contract.BookingViewModelContract;
import in.zollet.abhilashdas.bookingpage.viewmodel.contract.LifeCycle;
import in.zollet.abhilashdas.bookingpage.viewmodel.contract.RequestStates;

import static in.zollet.abhilashdas.bookingpage.viewmodel.contract.RequestStates.REQUEST_FAILED;
import static in.zollet.abhilashdas.bookingpage.viewmodel.contract.RequestStates.REQUEST_SUCCEEDED;

public class BookingViewModel extends BaseViewModel implements BookingViewModelContract.ViewModel,ErrorModel.ErrorActionListner  {


    private static final String REQUEST_BOOKING_DATA = "booking_data_request";
    @Inject
    public BookingManager manager;




    public ViewPagerConfiguration viewPagerConfiguration = new ViewPagerConfiguration();
    public ObservableField<LayoutProvider> viewpagerLayoutProvider = new ObservableField<>();
    public ViewPagerList collectionViewPagerList = new ViewPagerList(viewpagerLayoutProvider,this);

    public ObservableBoolean up = new ObservableBoolean(false);

    private Map<String, Map<String,List<SlotDetail>>> slots;

    private BookingViewModelContract.View viewCallback;

    public BookingViewModel(){
        BookingApplication.getInstance().component().inject(this);

    }

    @Override
    public void onViewStarted(@NonNull LifeCycle.View viewCallback) {
        this.viewCallback = (BookingViewModelContract.View) viewCallback;
        doApiCall();
    }

    @SuppressWarnings("unchecked")
    private void doApiCall(){
        compositeSubscription.add(handleErrorObservable(manager.getSlotDetail(getNetworkState(REQUEST_BOOKING_DATA)),viewCallback, this,true).subscribe(new BookingObserver(REQUEST_BOOKING_DATA)));
    }

    @Override
    public void onViewResumed() {
        NetworkState state = getNetworkState(REQUEST_BOOKING_DATA);

        @RequestStates.RequestState int requestState = state.getRequestState();
        if (requestState == REQUEST_SUCCEEDED) {
            ServerResponse response = (ServerResponse) state.getLastResponce();
            onBookingDataCompleted(response);
        } else if (requestState == REQUEST_FAILED) {
            onBookingDataFailed(getNetworkState(REQUEST_BOOKING_DATA).getLastError());
        }
    }

    private void onBookingDataFailed(Throwable throwable) {
        if(viewCallback!=null) {
            viewCallback.showError(new Exception(throwable.getMessage()));
            viewCallback.hideProgress();
        }
    }

    @SuppressWarnings("unchecked")
    private void onBookingDataCompleted(ServerResponse response) {
        slots = response.getSlots();

        collectionViewPagerList.clear();
        Set<String> dates = response.getSlots().keySet();

        for (String title: dates) {
            collectionViewPagerList.add(new ViewPagerMap(R.layout.item_viewpager,title, Util.parseDate(title,Util.YYYYMMDD,Util.DDE)));
        }
    }

    public void onCardClick(RecylerViewHolder holder, ImageView ivReveal){
        /*final int expandState = holder.getExpandStateFlags();

        if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_UPDATED) != 0) {
            int bgResId;
            boolean isExpanded;
            boolean animateIndicator = ((expandState & ExpandableItemConstants.STATE_FLAG_HAS_EXPANDED_STATE_CHANGED) != 0);

            if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_EXPANDED) != 0) {
                isExpanded = true;
            } else {
                isExpanded = false;
            }

            holder.setExpandStateFlags();

//            holder.setExpandedState(isExpanded, animateIndicator);
        }*/
    }


    @Override
    public void onViewStopped() {

    }

    public Map<String, List<SlotDetail>> getSlotList(String key){
        return slots.get(key);
    }

    @Override
    public void onErrorActionClicked() {
        doApiCall();
    }




    public class BookingObserver extends MaybeNetworkObserver<ServerResponse> {

        public BookingObserver(String tag) {
            super(tag);
        }

        @Override
        public void onNext(ServerResponse response) {
            super.onNext(response);
            onBookingDataCompleted(response);


           /* for (int i = 0; i < response.getSlots().size(); i++) {

                collectionViewPagerList.add(new ViewPagerMap(R.layout.item_viewpager,"",response.getSlots(). ));
                collectionViewPagerList.add(new ViewPagerMap(R.layout.item_viewpager,"","Image" ));
                collectionViewPagerList.add(new ViewPagerMap(R.layout.item_viewpager,"","MileStone" ));
            }
            collectionViewPagerList.add(new ViewPagerMap(R.layout.item_viewpager,"","Video" ));
            collectionViewPagerList.add(new ViewPagerMap(R.layout.item_viewpager,"","Image" ));
            collectionViewPagerList.add(new ViewPagerMap(R.layout.item_viewpager,"","MileStone" ));
            List*/

        }
    }



}
