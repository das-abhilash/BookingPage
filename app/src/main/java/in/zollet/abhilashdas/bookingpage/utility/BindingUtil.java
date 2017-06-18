package in.zollet.abhilashdas.bookingpage.utility;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

import java.util.List;
import java.util.Map;

import in.zollet.abhilashdas.bookingpage.R;
import in.zollet.abhilashdas.bookingpage.configuration.ViewPagerConfiguration;
import in.zollet.abhilashdas.bookingpage.customviews.CircleCropImageTransformation;
import in.zollet.abhilashdas.bookingpage.customviews.CustumDecoration;
import in.zollet.abhilashdas.bookingpage.view.adapter.BaseRecyclerAdapter;
import in.zollet.abhilashdas.bookingpage.view.adapter.ExapndableAdapter;
import in.zollet.abhilashdas.bookingpage.view.adapter.viewpageradapter.BasePagerAdapter;
import in.zollet.abhilashdas.bookingpage.view.adapter.viewpageradapter.GenericPagerAdapter;
import in.zollet.abhilashdas.bookingpage.view.adapter.viewpageradapter.LayoutProvider;
import in.zollet.abhilashdas.bookingpage.viewmodel.BaseViewModel;


public class BindingUtil {

    @BindingAdapter({"layoutProvider", "viewPagerConfig", "tabProperty"})
    public static <T> void setPagerAdapter(ViewPager viewPager, LayoutProvider layoutProvider, ViewPagerConfiguration viewPagerConfiguration, TabLayout tabLayout) {

        if (viewPagerConfiguration == null) {
            return;
        }

        viewPager.setClipToPadding(viewPagerConfiguration.isClipToPadding());
        viewPager.setPageMargin(viewPagerConfiguration.getViewPagerMargin());
        viewPager.setPadding(viewPagerConfiguration.getPaddingLeft(), viewPagerConfiguration.getPaddingTop(), viewPagerConfiguration.getPaddingRight(), viewPagerConfiguration.getPaddingBottom());
        viewPager.setOffscreenPageLimit(viewPagerConfiguration.getOffScreenpageLimit());
        if (viewPager.getAdapter() == null) {
            viewPager.setAdapter(new GenericPagerAdapter(layoutProvider));
        } else {
            BasePagerAdapter basePagerAdapter = (BasePagerAdapter) viewPager.getAdapter();
            basePagerAdapter.setLayoutProvider(layoutProvider);
            basePagerAdapter.notifyDataSetChanged();
        }

        if(tabLayout !=null){
            setTabProperty(tabLayout,layoutProvider);
        }
    }

    @BindingAdapter({"listItem","viewModel"})
    public static <T>void setListAdapter(RecyclerView recyclerView, Map<?,?> listItem, BaseViewModel viewModel){

        BaseRecyclerAdapter<T> baseAdapter= (BaseRecyclerAdapter<T>) recyclerView.getAdapter();
        if(baseAdapter==null){

            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL,false);
            RecyclerViewExpandableItemManager expMgr = new RecyclerViewExpandableItemManager(null);

            ExapndableAdapter adapter = new ExapndableAdapter(viewModel,R.layout.adapter_quater_time, R.layout.adapter_booking_time);
            adapter.updateList(listItem);
            recyclerView.setAdapter(expMgr.createWrappedAdapter(adapter));
            recyclerView.setLayoutManager(linearLayoutManager);
            expMgr.attachRecyclerView(recyclerView);
            recyclerView.addItemDecoration(new CustumDecoration());
            ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        }else {

            baseAdapter.updateList(listItem);
        }


    }

    @BindingAdapter("custumProgress")
    public static void setCustumProgressBar(ImageView imageView,String drawableRes){

        Glide.with(imageView.getContext())
                .load(R.drawable.custum_progress_gif)
                .into(imageView);

    }


    @BindingAdapter("tabProperty")
    public static void setTabProperty( TabLayout tabLayout, LayoutProvider layoutProvider) {

        for (int i = 0; i < layoutProvider.getSize(); i++) {
            if (tabLayout.getTabAt(i) != null) {
                View view = LayoutInflater.from(tabLayout.getContext()).inflate(R.layout.layout_custom_tab, null);

                TextView tabDate = (TextView) view.findViewById(R.id.tabDate);
                TextView tabDay = (TextView) view.findViewById(R.id.tabDay);

                tabDate.setText(Util.parseDate(layoutProvider.getTitle(i),Util.DDE,Util.DD));
                tabDay.setText(Util.parseDate(layoutProvider.getTitle(i),Util.DDE,Util.E));
                tabLayout.getTabAt(i).setCustomView(view);
            }
        }
    }
}
