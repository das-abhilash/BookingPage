package in.zollet.abhilashdas.bookingpage.view.adapter.viewpageradapter;

public interface LayoutProvider {


     int getLayoutId(int pos);
     <T>T getModel(int pos);
     Object getHandler();

     int getSize();
     String getTitle(int pos);
}