package com.example.nesthabit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.model.bean.Nest;

import java.util.List;

/**
 * Created by dizzylay on 2018/4/29.
 */
public class NestSelectAdapter extends RecyclerView.Adapter<NestSelectAdapter.ViewHolder> {

    private List<Nest> nestList;
    private ItemOnClickListener itemOnClickListener;

    public NestSelectAdapter(List<Nest> nestList) {
        this.nestList = nestList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nestName;

        public ViewHolder(View itemView) {
            super(itemView);
            nestName = itemView.findViewById(R.id.select_nest_name);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_nest_item,
                parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnClickListener.onItemClick(v, (Integer) v.getTag());
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Nest nest = nestList.get(position);
        holder.nestName.setText(nest.getName());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return nestList.size();
    }

    public void setItemOnClickListener(ItemOnClickListener clickListener) {
        this.itemOnClickListener = clickListener;
    }
}
