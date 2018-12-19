package com.seemantov.pokmy.pokmy.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seemantov.pokmy.R;
import com.seemantov.pokmy.base.BaseAdapter;
import com.seemantov.pokmy.base.BaseDiffCallback;
import com.seemantov.pokmy.base.BaseViewHolder;
import com.seemantov.pokmy.data.source.local.entity.Leave;
import com.seemantov.pokmy.data.source.local.entity.Salary;
import com.seemantov.pokmy.databinding.ItemLeaveBinding;
import com.seemantov.pokmy.databinding.ItemSalaryBinding;

import java.util.List;



public class LeavesAdapter extends BaseAdapter<Leave, LeavesAdapter.ViewHolder> {


    public LeavesAdapter() {
        super();
    }

    @Override
    public LeavesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leave, parent, false);
        return new LeavesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LeavesAdapter.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        Leave item = items.get(position);
        holder.bind(item);
    }

    @Override
    public void change(List<Leave> items) {
        change(new LeavesAdapter.NewsDiffCallback(this.items, items));
    }

    public class ViewHolder extends BaseViewHolder {

        private final Context context;
        private final ItemLeaveBinding binding;

        public ViewHolder(View view) {
            super(view);
            this.context = view.getContext();
            this.binding = DataBindingUtil.bind(view);
        }

        public void bind(Leave item) {
            if (binding.getLeave() != null) {
                binding.getLeave().setLeave(item);
            } else {
                //movie.setDate("14/01/2011");
                binding.setLeave(new LeaveItem(context,item));
            }
            binding.executePendingBindings();
        }
    }

    public class NewsDiffCallback extends BaseDiffCallback<Leave> {

        public NewsDiffCallback(List<Leave> oldList, List<Leave> newList) {
            super(oldList, newList);
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            // add a unique ID property on Movie and expose a getId() method
            return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Leave oldNews = oldList.get(oldItemPosition);
            Leave newNews = newList.get(newItemPosition);
            return oldNews.getId() == newNews.getId();
        }
    }


}
