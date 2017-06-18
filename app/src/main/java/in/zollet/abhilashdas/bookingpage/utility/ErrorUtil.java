package in.zollet.abhilashdas.bookingpage.utility;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import in.zollet.abhilashdas.bookingpage.R;
import in.zollet.abhilashdas.bookingpage.dataprovider.exception.NoInternetException;
import in.zollet.abhilashdas.bookingpage.model.ErrorModel;


public class ErrorUtil {


    public static ErrorModel getOnErrorErrorModel(Exception e, Context context, ErrorModel.ErrorActionListner listner, boolean... isButtonVisibility ){
        ErrorModel errorModel = listner != null ? new ErrorModel(listner) : new ErrorModel ();
        errorModel.setErrorDrawable(ErrorUtil.getErrorDrawable( e,context));
        errorModel.setErrorTitle(ErrorUtil.getErrorText( e,context));
        errorModel.setButtonText(ErrorUtil.getErrorButtonText( e,context));
        errorModel.setVisibility(true);
        if(isButtonVisibility.length > 0) errorModel.setButtonVisibility(isButtonVisibility[0]);
        else errorModel.setButtonVisibility(false);

        return errorModel;
    }

    public static ErrorModel getOnErrorErrorModel(Exception e, Context context){
        return getOnErrorErrorModel(e,context,null);
    }

    public static Drawable getErrorDrawable(Exception e, Context context){
        if(e instanceof NoInternetException){
            return ContextCompat.getDrawable(context, R.drawable.ic_no_internet);
        } else{
            return null;
        }
    }

    public static String getErrorButtonText(Exception e, Context context){
        if(e instanceof NoInternetException){
            return context.getResources().getString(R.string.no_internet_Button_text);
        } else{
            return null;
        }
    }

    public static String getErrorText(Exception e, Context context){
        if(e instanceof NoInternetException ){
            return context.getResources().getString(R.string.no_internet_text);
        } else {
            return "";
        }
    }

}
