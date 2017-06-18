package in.zollet.abhilashdas.bookingpage.view.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;
import in.zollet.abhilashdas.bookingpage.BR;
import in.zollet.abhilashdas.bookingpage.view.adapter.BaseRecyclerAdapter;
import in.zollet.abhilashdas.bookingpage.viewmodel.BaseViewModel;

public class RecylerViewHolder extends AbstractExpandableItemViewHolder {

    private final ViewDataBinding mBinding;

    public RecylerViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bind(Object item, int position, BaseRecyclerAdapter adapter, BaseViewModel viewModel,boolean isExpanded) {
        mBinding.setVariable(BR.item, item);
        mBinding.setVariable(BR.position, position);
        mBinding.setVariable(BR.model, viewModel);
        mBinding.setVariable(BR.adapter, adapter);
//        mBinding.setVariable(BR.viewHolder, this);
        mBinding.setVariable(BR.isExpanded, isExpanded);
        mBinding.executePendingBindings();
    }
}