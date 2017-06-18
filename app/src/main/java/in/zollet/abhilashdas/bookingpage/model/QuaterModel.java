package in.zollet.abhilashdas.bookingpage.model;

import java.util.Random;
import java.util.UUID;

public class QuaterModel {

    public QuaterModel(String quater, int slotsAvaialble) {
        this.quater = quater;
        this.slotsAvaialble = slotsAvaialble;
    }

    private String quater;
    private int  slotsAvaialble;

    public String getQuater() {
        return quater;
    }

    public void setQuater(String quater) {
        this.quater = quater;
    }

    public String  getSlotsAvaialble() {
        return slotsAvaialble + " Slots available";
    }

    public void setSlotsAvaialble(int slotsAvaialble) {
        this.slotsAvaialble = slotsAvaialble;
    }
}
