package in.zollet.abhilashdas.bookingpage.view.adapter.viewpageradapter;

import java.util.HashMap;

public class ViewPagerMap<T> extends HashMap<Integer,T> {

    private String title;

    public ViewPagerMap(int layoutId,T model) {
        super(1);
        put(layoutId,model);
    }

    public ViewPagerMap(int layoutId){
        this(layoutId,null);
    }

    public ViewPagerMap(int layoutId,T model,String title) {
        this(layoutId,model);
        this.title = title;
    }

    public void setModel(T model){
        put((Integer) get(keySet().iterator().next()),model);
    }

    public String getTitle() {
        return title;
    }
}