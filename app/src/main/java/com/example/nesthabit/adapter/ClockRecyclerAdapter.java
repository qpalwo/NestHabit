package com.example.nesthabit.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.model.bean.ClockInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClockRecyclerAdapter extends RecyclerView.Adapter<ClockRecyclerAdapter.ViewHolder> {
    private List<ClockInfo> clockInfos = new ArrayList<>();
    private ItemOnClickListener itemOnClickListener;

    public ClockRecyclerAdapter(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.clock_item_img)
        ImageView clockItemImg;
        @BindView(R.id.clock_item_slogan)
        TextView clockItemSlogan;
        @BindView(R.id.clock_item_logo)
        ImageView clockItemLogo;
        @BindView(R.id.clock_item_time)
        TextView clockItemTime;
        @BindView(R.id.clock_item_day)
        TextView clockItemDay;
        @BindView(R.id.clock_item_lefttime)
        TextView clockItemLefttime;
        @BindView(R.id.clock_item_checked)
        RelativeLayout clockItemChecked;
        @BindView(R.id.clock_item_switch)
        Switch clockItemSwitch;
        ItemOnClickListener itemOnClickListener;

        public ViewHolder(View itemView, ItemOnClickListener itemOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemOnClickListener = itemOnClickListener;
        }

        @Override
        public void onClick(View v) {
            itemOnClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clock_item, parent, false);
        return new ViewHolder(view, itemOnClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return clockInfos.size();
    }

    public void upDate(@NonNull List<ClockInfo> clockInfos) {
        this.clockInfos.addAll(clockInfos);
        notifyDataSetChanged();
    }

}