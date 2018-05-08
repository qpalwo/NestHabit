package com.example.nesthabit.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.model.bean.Sound;

import java.util.List;

public class SystemSoundAdapter extends RecyclerView.Adapter<SystemSoundAdapter.ViewHolder> {

    private List<Sound> soundList;
    private ItemOnClickListener itemOnClickListener;
    private int currentSelectedSound = 0;

    public SystemSoundAdapter(List<Sound> soundList) {
        this.soundList = soundList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView soundName;
        ImageView isSelected;

        ViewHolder(View itemView) {
            super(itemView);
            soundName = itemView.findViewById(R.id.system_sound_name);
            isSelected = itemView.findViewById(R.id.system_sound_is_selected);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.system_sound_item,
                parent, false);
        view.setOnClickListener(v -> itemOnClickListener.onItemClick(v, (Integer) v.getTag()));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sound sound = soundList.get(position);
        holder.soundName.setText(sound.getName());
        holder.itemView.setTag(position);
        if (position == currentSelectedSound) {
            holder.soundName.setTextColor(Color.parseColor("#5C6B73"));
            holder.isSelected.setVisibility(View.VISIBLE);
        } else {
            holder.soundName.setTextColor(Color.parseColor("#78909C"));
            holder.isSelected.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return soundList.size();
    }

    public void setItemOnClickListener(ItemOnClickListener clickListener) {
        this.itemOnClickListener = clickListener;
    }

    public int getCurrentSelectedSound() {
        return currentSelectedSound;
    }

    public void setCurrentSelectedSound(int currentSelectedSound) {
        this.currentSelectedSound = currentSelectedSound;
    }
}
