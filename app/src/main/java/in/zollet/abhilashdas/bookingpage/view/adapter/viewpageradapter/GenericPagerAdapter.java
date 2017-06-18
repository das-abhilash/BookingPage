package in.zollet.abhilashdas.bookingpage.view.adapter.viewpageradapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import in.zollet.abhilashdas.bookingpage.BR;

public class GenericPagerAdapter extends BasePagerAdapter {


    LayoutProvider layoutProvider;

    public GenericPagerAdapter(LayoutProvider layoutProvider) {
        this.layoutProvider = layoutProvider;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), layoutProvider.getLayoutId(position), container, false);
        viewDataBinding.setVariable(BR.viewPagetItem,layoutProvider.getModel(position));
        viewDataBinding.setVariable(BR.handler,layoutProvider.getHandler());
//        viewDataBinding.setVariable(BR.position,position);
        viewDataBinding.executePendingBindings();
        container.addView(viewDataBinding.getRoot());

        return viewDataBinding;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewDataBinding binding = (ViewDataBinding) object;
        container.removeView(binding.getRoot());
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((ViewDataBinding) object).getRoot() == view;
    }

    @Override
    public int getCount() {
        return layoutProvider.getSize();
    }

    @Override
    public void setLayoutProvider(LayoutProvider layoutProvider) {
        this.layoutProvider=layoutProvider;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return layoutProvider.getTitle(position);
    }
}