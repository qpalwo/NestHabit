package com.example.nesthabit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.example.nesthabit.R;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.model.bean.Nest;
import com.example.nesthabit.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;

public class NestContentRecyclerAdapter extends RecyclerView.Adapter<NestContentRecyclerAdapter.ViewHolder> {
    List<Nest> nests;
    ItemOnClickListener itemOnClickListener;

    public NestContentRecyclerAdapter(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nest_item, parent, false);
        return new ViewHolder(view, itemOnClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Nest nest = nests.get(position);
        holder.nestName.setText(nest.getName());
        if (nest.getMembersLimit() != 0) {
            holder.nestItemProgress.setCurrentProgress(nest.getMemberAmount() / nest.getMembersLimit());
        } else {
            holder.nestItemProgress.setCurrentProgress(0.75f);
        }
        if (!AVUser.getCurrentUser().getUsername().equals(nest.getOwner())){
            holder.flagMaster.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (nests != null) {
            return nests.size();
        } else {
            return 0;
        }
    }

    public void changeData(List<Nest> nests){
        if (this.nests == null){
            this.nests = nests;
        }else {
            this.nests.addAll(nests);
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.nest_name)
        TextView nestName;
        @BindView(R.id.flag_master)
        ImageView flagMaster;
        @BindView(R.id.nest_item_progress)
        ProgressBar nestItemProgress;
        ItemOnClickListener itemOnClickListener;

        public ViewHolder(View itemView, ItemOnClickListener itemOnClickListener) {
            super(itemView);
            this.itemOnClickListener = itemOnClickListener;
        }

        @Override
        public void onClick(View v) {
            itemOnClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
