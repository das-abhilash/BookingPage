package in.zollet.abhilashdas.bookingpage.model;

import java.util.List;
import java.util.Map;

public class ServerResponse {

    private Map<String, Map<String,List<SlotDetail>>> slots;

    public Map<String, Map<String, List<SlotDetail>>> getSlots() {
        return slots;
    }

    public void setSlots(Map<String, Map<String, List<SlotDetail>>> slots) {
        this.slots = slots;
    }
}
