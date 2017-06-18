package in.zollet.abhilashdas.bookingpage.configuration;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import in.zollet.abhilashdas.bookingpage.BR;
import in.zollet.abhilashdas.bookingpage.customviews.CirclePageIndicator;


public class ViewPagerConfiguration extends BaseObservable {

    private int viewPagerMargin;
    private boolean clipToPadding;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;
    private int offScreenpageLimit;
    private CirclePageIndicator circlePageIndicator;

    private int circlepageColor;
    private int circlePagefillColor;

    public ViewPagerConfiguration() {
        viewPagerMargin=40;
        clipToPadding=true;
        offScreenpageLimit=3;
    }


    public void setCirclepageColor(int circlepageColor) {
        this.circlepageColor = circlepageColor;
        //  notifyPropertyChanged(BR.ci);
    }

    public void setCirclePagefillColor(int circlePagefillColor) {
        this.circlePagefillColor = circlePagefillColor;
        //  notifyPropertyChanged(BR.circlepageColor);
    }

    public int getCirclepageColor() {
        return circlepageColor;
    }

    public int getCirclePagefillColor() {
        return circlePagefillColor;
    }

    public int getViewPagerMargin() {
        return viewPagerMargin;
    }

    @Bindable
    public void setViewPagerMargin(int viewPagerMargin) {
        this.viewPagerMargin = viewPagerMargin;
        notifyPropertyChanged(BR.viewPagerMargin);
    }

    public boolean isClipToPadding() {
        return clipToPadding;
    }

    @Bindable
    public void setClipToPadding(boolean clipToPadding) {
        this.clipToPadding = clipToPadding;
        notifyPropertyChanged(BR.clipToPadding);
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    @Bindable
    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
        notifyPropertyChanged(BR.paddingLeft);
    }

    public int getPaddingRight() {
        return paddingRight;

    }

    @Bindable
    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
        notifyPropertyChanged(BR.paddingRight);
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    @Bindable
    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
        notifyPropertyChanged(BR.paddingTop);
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    @Bindable
    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
        notifyPropertyChanged(BR.paddingBottom);
    }

    public int getOffScreenpageLimit() {
        return offScreenpageLimit;
    }

    @Bindable
    public void setOffScreenpageLimit(int offScreenpageLimit) {
        this.offScreenpageLimit = offScreenpageLimit;
        notifyPropertyChanged(BR.offScreenpageLimit);
    }

    public CirclePageIndicator getCirclePageIndicator() {
        return circlePageIndicator;
    }

    @Bindable
    public void setCirclePageIndicator(CirclePageIndicator circlePageIndicator) {
        this.circlePageIndicator = circlePageIndicator;
        notifyPropertyChanged(BR.circlePageIndicator);
    }
}
