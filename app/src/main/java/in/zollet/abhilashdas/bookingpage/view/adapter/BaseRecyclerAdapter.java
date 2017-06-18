package in.zollet.abhilashdas.bookingpage.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.IntRange;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;

import java.util.List;
import java.util.Map;

import in.zollet.abhilashdas.bookingpage.R;
import in.zollet.abhilashdas.bookingpage.model.SlotDetail;
import in.zollet.abhilashdas.bookingpage.view.viewholder.RecylerViewHolder;
import in.zollet.abhilashdas.bookingpage.viewmodel.BaseViewModel;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public abstract class BaseRecyclerAdapter<T> extends AbstractExpandableItemAdapter<RecylerViewHolder, RecylerViewHolder> {

    @Override
    public RecylerViewHolder onCreateGroupViewHolder(ViewGroup parent, @IntRange(from = -8388608L, to = 8388607L) int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new RecylerViewHolder(binding);
    }

    @Override
    public RecylerViewHolder onCreateChildViewHolder(ViewGroup parent, @IntRange(from = -8388608L, to = 8388607L) int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new RecylerViewHolder(binding);
    }

    @Override
    public void onBindGroupViewHolder(RecylerViewHolder holder, int groupPosition, @IntRange(from = -8388608L, to = 8388607L) int viewType) {
        Object obj = getParentObjForPosition(groupPosition);

        final int expandState = holder.getExpandStateFlags();
        boolean isExpanded = false;
        if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_UPDATED) != 0) {
            int bgResId;

            boolean animateIndicator = ((expandState & ExpandableItemConstants.STATE_FLAG_HAS_EXPANDED_STATE_CHANGED) != 0);


            if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_EXPANDED) != 0) {
                isExpanded = true;
            } else {
                isExpanded = false;
            }
        }
        holder.bind(obj, groupPosition, this, getViewModel(), isExpanded);
    }

    @Override
    public void onBindChildViewHolder(RecylerViewHolder holder, int groupPosition, int childPosition, @IntRange(from = -8388608L, to = 8388607L) int viewType) {
        Object obj = getChildObjForPosition(groupPosition, childPosition);
        holder.bind(obj, childPosition, this, getViewModel(), false);
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(RecylerViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        if (getChildData(groupPosition).size() > 0)
            return true;
        else
            return false;
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        return getLayoutIdForParentPosition(groupPosition);
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return getLayoutIdForChildPosition(groupPosition, childPosition);
    }

    public abstract BaseViewModel getViewModel();

    public abstract void setViewModel(BaseViewModel viewModel);

    protected abstract Object getChildObjForPosition(int groupPosition, int position);

    protected abstract Object getParentObjForPosition(int position);


    protected abstract int getLayoutIdForParentPosition(int position);

    protected abstract int getLayoutIdForChildPosition(int groupPosition, int position);


    public abstract void updateList(Map<?, ?> map);

    public abstract List<T> getParentData();

    public abstract List<T> getChildData(int groupPosition);

    public abstract void remvoeItemFromList(int position);
}
