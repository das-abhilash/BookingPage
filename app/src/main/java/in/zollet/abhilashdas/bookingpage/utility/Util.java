package in.zollet.abhilashdas.bookingpage.utility;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.StringDef;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import in.zollet.abhilashdas.bookingpage.R;
import in.zollet.abhilashdas.bookingpage.customviews.CustomProgressBar;

public class Util {

    private static CustomProgressBar custumProgresBar;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({YYYYMMDDHHMMSS, YYYYMMDD, HMMA, EDD,DDE,DD,E})
    public @interface DateFormat {}

    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String HMMA = "h:mm a";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String EDD = "E dd";
    public static final String DDE = "dd E";
    public static final String DD = "dd";
    public static final String E = "E";

    public static String parseDate(String time, String input, String output) {

        SimpleDateFormat inputFormat = new SimpleDateFormat(input,Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(output,Locale.ENGLISH);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String toTitleCase(String input) {
        if (input==null)return "" ;
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

    public static void showProgressDialog(FragmentManager manager, String TAG, boolean... isCancelable){


        if(custumProgresBar!=null){
            custumProgresBar.dismiss();
            custumProgresBar=null;
        }

        if(custumProgresBar==null ) {
            custumProgresBar = new CustomProgressBar();
        }
        custumProgresBar.show(manager,TAG);

    }

    public static long generateRandomNumber() {
        long range = 1234567L;
        Random r = new Random();
        long number = (long)(r.nextDouble()*range);
        return number;
    }

    public static void hideCustumProgressBar(){
        if(custumProgresBar!=null){
            custumProgresBar.dismiss();
        }
    }

    public static void showErrorSnackBar(View view, Context context, String msg) {
        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        sbView.setBackgroundColor(Color.RED);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

}
