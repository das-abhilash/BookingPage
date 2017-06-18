package in.zollet.abhilashdas.bookingpage.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.zollet.abhilashdas.bookingpage.utility.Util;

public class SlotDetail {

    private String end_time;
    private boolean is_booked;
    private boolean is_expired;
    private int slot_id;
    private String start_time;

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public boolean is_booked() {
        return is_booked;
    }

    public void setIs_booked(boolean is_booked) {
        this.is_booked = is_booked;
    }

    public boolean is_expired() {
        return is_expired;
    }

    public void setIs_expired(boolean is_expired) {
        this.is_expired = is_expired;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getTimeSlot(){
        return Util.parseDate(start_time,Util.YYYYMMDDHHMMSS,Util.HMMA) +" - " + Util.parseDate(end_time,Util.YYYYMMDDHHMMSS,Util.HMMA);
    }

    public boolean isSlotEnabled(){
        return !(is_booked || is_expired);
    }
}
