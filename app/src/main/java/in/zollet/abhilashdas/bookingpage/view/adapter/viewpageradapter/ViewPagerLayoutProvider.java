package in.zollet.abhilashdas.bookingpage.view.adapter.viewpageradapter;

import java.util.List;

public abstract class ViewPagerLayoutProvider implements LayoutProvider {
    public abstract <T>List<ViewPagerMap<T>> getProviderList();

    @Override
    public int getLayoutId(int pos) {
        return  getProviderList().get(pos).keySet().iterator().next();
    }

    @Override
    public <T> T getModel(int pos) {

        int key=getProviderList().get(pos).keySet().iterator().next();

        return (T) getProviderList().get(pos).get(key);
    }



    @Override
    public int getSize() {
        return getProviderList().size();
    }

    @Override
    public String getTitle(int pos) {
        return getProviderList().get(pos).getTitle();
    }
}