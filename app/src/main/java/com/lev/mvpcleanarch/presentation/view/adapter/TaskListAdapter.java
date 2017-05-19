package com.lev.mvpcleanarch.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lev.mvpcleanarch.R;
import com.lev.mvpcleanarch.domain.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author: Lev
 * Date: 17.05.2017
 */

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.description)
        TextView description;

        @BindView(R.id.checkBox)
        CheckBox checkBox;

        ViewHolder(ViewGroup parent) {
            super(mInflater.inflate(R.layout.task_list_item, parent, false));
            ButterKnife.bind(this, itemView);
        }

        void setOnClickListener(View.OnClickListener listener) {
            itemView.setOnClickListener(listener);
        }
    }

    public interface OnItemClickListener {
        void onClick(String id);
    }

    private final LayoutInflater mInflater;
    private final OnItemClickListener mListener;
    private final List<Task> mData = new ArrayList<>();

    public TaskListAdapter(@NonNull Context context, @Nullable OnItemClickListener listener) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Task task = mData.get(position);
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
        holder.checkBox.setChecked(task.isCompleted());
        if (mListener != null)
            holder.setOnClickListener(v -> mListener.onClick(mData.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(Collection<Task> tasks) {
        mData.clear();
        mData.addAll(tasks);
        notifyDataSetChanged();
    }
}
