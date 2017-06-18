package in.zollet.abhilashdas.bookingpage.view.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import in.zollet.abhilashdas.bookingpage.model.QuaterModel;
import in.zollet.abhilashdas.bookingpage.model.SlotDetail;
import in.zollet.abhilashdas.bookingpage.view.viewholder.RecylerViewHolder;
import in.zollet.abhilashdas.bookingpage.viewmodel.BaseViewModel;

public class ExapndableAdapter extends BaseRecyclerAdapter {


    private Map<String, List<SlotDetail>> exapandleMap = new HashMap<>();

    private List<QuaterModel> parentList = new ArrayList<>();
    private BaseViewModel baseViewModel;
    private int parentLayout;
    private int childLayout;

    public ExapndableAdapter(BaseViewModel baseViewModel,int parentLayout, int childLayout){
        setHasStableIds(true); // it is required
        this.baseViewModel = baseViewModel;
        this.parentLayout = parentLayout;
        this.childLayout = childLayout;
    }



    @Override
    public BaseViewModel getViewModel() {
        return baseViewModel;
    }

    @Override
    public void setViewModel(BaseViewModel viewModel) {
        this.baseViewModel = baseViewModel;
    }

    @Override
    protected Object getChildObjForPosition(int groupPosition, int childPosition) {
        List<String> list = new ArrayList<>(exapandleMap.keySet());
        return exapandleMap.get(list.get(groupPosition)).get(childPosition);
    }

    @Override
    protected Object getParentObjForPosition(int position) {
        List<String> list = new ArrayList<>(exapandleMap.keySet());
        return new QuaterModel(list.get(position),exapandleMap.get(list.get(position)).size());
    }

    @Override
    protected int getLayoutIdForParentPosition(int position) {
        return parentLayout;
    }

    @Override
    protected int getLayoutIdForChildPosition(int groupPosition, int position) {
        return childLayout;
    }

    @Override
    public void updateList(Map map) {
        exapandleMap = (Map<String, List<SlotDetail>>) map;
        notifyDataSetChanged();
    }



    @Override
    public List getParentData() {
        return new ArrayList<>(exapandleMap.keySet());
    }

    @Override
    public List getChildData(int groupPosition) {

        List<String> list = new ArrayList<>(exapandleMap.keySet());
        return exapandleMap.get(list.get(groupPosition));

    }

    @Override
    public void remvoeItemFromList(int position) {

    }

    @Override
    public int getGroupCount() {
        return new ArrayList<>(exapandleMap.keySet()).size();
    }

    @Override
    public int getChildCount(int groupPosition) {
        List<String> list = new ArrayList<>(exapandleMap.keySet());
        return exapandleMap.get(list.get(groupPosition)).size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return ((SlotDetail)getChildObjForPosition(groupPosition,childPosition)).getSlot_id();
    }
}
