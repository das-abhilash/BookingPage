package in.zollet.abhilashdas.bookingpage.customviews;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CustumDecoration extends RecyclerView.ItemDecoration {


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left=2;
        outRect.right=2;
        outRect.bottom=2;
        /*if(parent.getChildAdapterPosition(view)==0){
            outRect.top=16;
        }*/


    }
}
