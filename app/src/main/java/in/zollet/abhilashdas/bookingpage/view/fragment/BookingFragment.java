package in.zollet.abhilashdas.bookingpage.view.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import in.zollet.abhilashdas.bookingpage.BookingApplication;
import in.zollet.abhilashdas.bookingpage.R;
import in.zollet.abhilashdas.bookingpage.databinding.FragmentBookingBinding;
import in.zollet.abhilashdas.bookingpage.model.ErrorModel;
import in.zollet.abhilashdas.bookingpage.utility.Constants;
import in.zollet.abhilashdas.bookingpage.viewmodel.contract.BookingViewModelContract;
import in.zollet.abhilashdas.bookingpage.viewmodel.contract.LifeCycle;
import in.zollet.abhilashdas.bookingpage.viewmodel.implementation.BookingViewModel;




public class BookingFragment extends BaseFragment implements BookingViewModelContract.View{

    String month;
    FragmentBookingBinding binding;

    public BookingFragment() {
        // Required empty public constructor
    }

    @Inject
    BookingViewModel bookingViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BookingApplication) getActivity().getApplication()).component().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_booking, container, false);

        setHasOptionsMenu(true);
        binding.tabLayout.setupWithViewPager(binding.viewpager);
        if(getArguments() != null && getArguments().getString(Constants.MONTH_KEY) != null) {  // read from prevoius fragment which motnh user has clicked
            month = getArguments().getString(Constants.MONTH_KEY);
        } else {  // for dummy purpose
            month ="June";
        }

        binding.setMonth(month);
        binding.setModel(bookingViewModel);

        return binding.getRoot();
    }


    @Override
    public LifeCycle.ViewModel getViewModel() {
        return bookingViewModel;
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void setErrorDrawable(ErrorModel errorModel) {
        binding.setErrorModel(errorModel);
    }

    @Override
    public void onBackPress() {

    }

    @Override
    public void onFinish() {

    }
}
