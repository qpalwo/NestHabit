package com.example.nesthabit.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.model.bean.Clock;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static com.example.nesthabit.model.DateUtil.convertTimeToFormat;
import static com.example.nesthabit.model.DateUtil.getNextClockTime;

public class ClockRecyclerAdapter extends RecyclerView.Adapter<ClockRecyclerAdapter.ViewHolder> {
    private List<Clock> clockInfos = new ArrayList<>();
    private ItemOnClickListener itemOnClickListener;
    private static final String TAG = "ClockRecyclerAdapter";

    public final static int LIST_ADD = 0;
    public final static int LIST_UPDATE = 1;
    public final static int LIST_DELETE = 2;

    public ClockRecyclerAdapter(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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

        ViewHolder(View itemView, ItemOnClickListener itemOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemOnClickListener = itemOnClickListener;
        }

        @OnClick({R.id.clock_item_switch})
        public void onViewClicked(View view) {
            itemOnClickListener.onItemClick(view, getAdapterPosition());
        }

        @OnLongClick({R.id.clock_item_checked})
        public boolean onViewLongClicked(View view) {
            itemOnClickListener.onItemClick(view, getAdapterPosition());
            return true;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clock_item, parent,
                false);
        return new ViewHolder(view, itemOnClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Clock clock = clockInfos.get(position);
        holder.clockItemSlogan.setText(clock.getTitle());
        String time = String.format(new Locale("en"), "%02d", clock.getTimeHour())
                + ":"
                + String.format(new Locale("en"), "%02d", clock.getTimeMin());
        holder.clockItemTime.setText(time);
        holder.clockItemSwitch.setChecked(clock.getIsOpen() == 1);
        if (holder.clockItemSwitch.isChecked()) {
            holder.itemView.setAlpha(1f);
            if (clock.getTimeHour() >= 6 && clock.getTimeHour() <= 18) {
                holder.clockItemImg.setImageResource(R.drawable.day);
            } else {
                holder.clockItemImg.setImageResource(R.drawable.night);
            }
        } else {
            holder.itemView.setAlpha(0.6f);
            if (clock.getTimeHour() >= 6 && clock.getTimeHour() <= 18) {
                holder.clockItemImg.setImageResource(R.drawable.day_unchecked);
            } else {
                holder.clockItemImg.setImageResource(R.drawable.night_unchecked);
            }
        }
        TextView clockDay = holder.clockItemDay;
        StringBuilder builder = new StringBuilder();
        if (clock.getDurationLevel() == 0x7f) {
            clockDay.setText("每天");
        } else {
            if ((clock.getDurationLevel() & 0x01) != 0) {
                builder.append("周日 ");
            }
            if ((clock.getDurationLevel() & 0x02) != 0) {
                builder.append("周一 ");
            }
            if ((clock.getDurationLevel() & 0x04) != 0) {
                builder.append("周二 ");
            }
            if ((clock.getDurationLevel() & 0x08) != 0) {
                builder.append("周三 ");
            }
            if ((clock.getDurationLevel() & 0x10) != 0) {
                builder.append("周四 ");
            }
            if ((clock.getDurationLevel() & 0x20) != 0) {
                builder.append("周五 ");
            }
            if ((clock.getDurationLevel() & 0x40) != 0) {
                builder.append("周六 ");
            }
            clockDay.setText(builder.toString());
        }
        TextView leftTime = holder.clockItemLefttime;
        leftTime.setText(convertTimeToFormat(getNextClockTime(clock)));
        Log.d(TAG, "onBindViewHolder: " + String.valueOf(getNextClockTime(clock)));
    }

    @Override
    public int getItemCount() {
        return clockInfos.size();
    }

    public void changeData(List<Clock> clockInfos, int state) {
        if (this.clockInfos == null) {
            this.clockInfos = new ArrayList<>();
        }
        switch (state) {
            case LIST_ADD:
                this.clockInfos.addAll(clockInfos);
                break;
            case LIST_UPDATE:
                this.clockInfos.clear();
                this.clockInfos.addAll(clockInfos);
                break;
            case LIST_DELETE:
                //TODO delete list
                break;
            default:
                break;
        }
        notifyDataSetChanged();
    }

}

