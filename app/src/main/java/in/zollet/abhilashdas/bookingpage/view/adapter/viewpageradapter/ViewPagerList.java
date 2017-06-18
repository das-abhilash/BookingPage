package in.zollet.abhilashdas.bookingpage.view.adapter.viewpageradapter;

import android.databinding.ObservableField;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerList extends ArrayList<ViewPagerMap> {


    public ObservableField<LayoutProvider> layoutProviderObservableField;
    public String pageTitle;
    public Object actionHandler;

    public ViewPagerList(ObservableField<LayoutProvider> layoutProviderObservableField) {
        this(layoutProviderObservableField,null);
    }

    public ViewPagerList(ObservableField<LayoutProvider> layoutProviderObservableField, Object actionHandler) {
        this.actionHandler=actionHandler;
        this.layoutProviderObservableField = layoutProviderObservableField;
        setLayoutProvider();
    }

    @Override
    public boolean add(ViewPagerMap viewPagerMap){
        boolean z=super.add(viewPagerMap);
        setLayoutProvider();
        return z;
    }

    public void addAll(List<ViewPagerMap> mapList){
        this.addAll(mapList);
        setLayoutProvider();
    }


    @Override
    public ViewPagerMap remove(int index) {

        ViewPagerMap viewPagerMap= super.remove(index);
        setLayoutProvider();
        return viewPagerMap;
    }

    @Override
    public boolean remove(Object o) {
        boolean z= super.remove(o);
        setLayoutProvider();
        return z;
    }

    private void setLayoutProvider() {
        layoutProviderObservableField.set(new ViewPagerLayoutProvider() {


            @Override
            public Object getHandler() {
                return actionHandler;
            }

            @Override
            public List<ViewPagerMap> getProviderList() {
                return ViewPagerList.this;
            }
        });
    }

    @Override
    public void clear() {
        super.clear();
        setLayoutProvider();
    }
}