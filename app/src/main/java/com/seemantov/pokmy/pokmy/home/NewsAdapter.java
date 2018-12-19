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
import com.seemantov.pokmy.databinding.ItemNewsBinding;

import java.util.List;



public class NewsAdapter extends BaseAdapter<News, NewsAdapter.ViewHolder> {
//public class NotificationAdapter {


    public NewsAdapter() {
        super();
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        News item = items.get(position);
        holder.bind(item);
    }

    @Override
    public void change(List<News> items) {
        change(new NewsAdapter.NewsDiffCallback(this.items, items));
    }

    public class ViewHolder extends BaseViewHolder {

        private final Context context;
        private final ItemNewsBinding binding;

        public ViewHolder(View view) {
            super(view);
            this.context = view.getContext();
            this.binding = DataBindingUtil.bind(view);
        }

        public void bind(News news) {
            if (binding.getNews() != null) {
                binding.getNews().setNews(news);
            } else {
                //movie.setDate("14/01/2011");
                binding.setNews(new NewsItem(context,news));
            }
            binding.executePendingBindings();
        }
    }

    public class NewsDiffCallback extends BaseDiffCallback<News> {

        public NewsDiffCallback(List<News> oldList, List<News> newList) {
            super(oldList, newList);
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            // add a unique ID property on Movie and expose a getId() method
            return oldList.get(oldItemPosition).get_id() == newList.get(newItemPosition).get_id();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            News oldNews = oldList.get(oldItemPosition);
            News newNews = newList.get(newItemPosition);
            return oldNews.get_id() == newNews.get_id();
        }
    }


}
