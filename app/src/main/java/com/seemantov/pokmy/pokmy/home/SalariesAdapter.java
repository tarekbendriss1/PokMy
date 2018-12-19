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
import com.seemantov.pokmy.data.source.local.entity.News;
import com.seemantov.pokmy.data.source.local.entity.Salary;
import com.seemantov.pokmy.databinding.ItemNewsBinding;
import com.seemantov.pokmy.databinding.ItemSalaryBinding;

import java.util.List;

public class SalariesAdapter extends BaseAdapter<Salary, SalariesAdapter.ViewHolder> {
//public class NotificationAdapter {


    public SalariesAdapter() {
        super();
    }

    @Override
    public SalariesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salary, parent, false);
        return new SalariesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SalariesAdapter.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        Salary item = items.get(position);
        holder.bind(item);
    }

    @Override
    public void change(List<Salary> items) {
        change(new SalariesAdapter.NewsDiffCallback(this.items, items));
    }

    public class ViewHolder extends BaseViewHolder {

        private final Context context;
        private final ItemSalaryBinding binding;

        public ViewHolder(View view) {
            super(view);
            this.context = view.getContext();
            this.binding = DataBindingUtil.bind(view);
        }

        public void bind(Salary salary) {
            if (binding.getSalary() != null) {
                binding.getSalary().setSalary(salary);
            } else {
                //movie.setDate("14/01/2011");
                binding.setSalary(new SalaryItem(context,salary));
            }
            binding.executePendingBindings();
        }
    }

    public class NewsDiffCallback extends BaseDiffCallback<Salary> {

        public NewsDiffCallback(List<Salary> oldList, List<Salary> newList) {
            super(oldList, newList);
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            // add a unique ID property on Movie and expose a getId() method
            return oldList.get(oldItemPosition).get_id() == newList.get(newItemPosition).get_id();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Salary oldNews = oldList.get(oldItemPosition);
            Salary newNews = newList.get(newItemPosition);
            return oldNews.get_id() == newNews.get_id();
        }
    }


}
