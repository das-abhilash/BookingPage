package in.zollet.abhilashdas.bookingpage.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

import in.zollet.abhilashdas.bookingpage.BR;

public class ErrorModel extends BaseObservable {

    private Drawable errorDrawable;
    private String errorTitle;
    private String errorSubTitle;
    private boolean visibility = true;
    private boolean buttonVisibility = false;
    private ErrorActionListner errorActionListner;
    private String buttonText;



    public ErrorModel(boolean visibility) {
        this.visibility = visibility;
    }

    public ErrorModel() {

    }

    public ErrorModel(ErrorActionListner errorActionListner) {
        this.errorActionListner = errorActionListner;
    }

    public ErrorActionListner getErrorActionListner() {
        return errorActionListner;
    }

    @Bindable
    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
        notifyPropertyChanged(BR.buttonText);
    }

    @Bindable
    public boolean getButtonVisibility() {
        return buttonVisibility;
    }

    public void setButtonVisibility(boolean buttonVisibility) {
        this.buttonVisibility = buttonVisibility;
        notifyPropertyChanged(BR.buttonVisibility);
    }

    public void setErrorActionListner(ErrorActionListner errorActionListner) {
        this.errorActionListner = errorActionListner;
    }

    @Bindable
    public Drawable getErrorDrawable() {
        return errorDrawable;
    }

    public void setErrorDrawable(Drawable errorDrawable) {
        this.errorDrawable = errorDrawable;
        notifyPropertyChanged(BR.errorDrawable);
    }

    @Bindable
    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
        notifyPropertyChanged(BR.errorTitle);
    }
    @Bindable
    public String getErrorSubTitle() {
        return errorSubTitle;
    }

    public void setErrorSubTitle(String errorSubTitle) {
        this.errorSubTitle = errorSubTitle;
        notifyPropertyChanged(BR.errorSubTitle);
    }
    @Bindable
    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
        notifyPropertyChanged(BR.visibility);
    }


    public interface ErrorActionListner{
        void onErrorActionClicked();
    }

}
