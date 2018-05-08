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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NestContentRecyclerAdapter extends RecyclerView.Adapter<NestContentRecyclerAdapter.ViewHolder> {
    private List<Nest> nests;
    private ItemOnClickListener itemOnClickListener;
    public final static int LIST_ADD = 0;
    public final static int LIST_UPDATE = 1;
    public final static int LIST_DELETE = 2;

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
        if (nest.getName() != null) {
            holder.nestName.setText(nest.getName());
        }
        if (nest.getMembersLimit() != 0) {
            holder.nestItemProgress.setCurrentProgress(nest.getMemberAmount() / nest.getMembersLimit());
        } else {
            holder.nestItemProgress.setCurrentProgress(0.75f);
        }
//        if (!AVUser.getCurrentUser().getUsername().equals(nest.getOwner())) {
//            holder.flagMaster.setVisibility(View.INVISIBLE);
//        }
        // TODO: 18-5-6 is the owner tag
    }

    @Override
    public int getItemCount() {
        if (nests != null) {
            return nests.size();
        } else {
            return 0;
        }
    }

    public void changeData(List<Nest> nests, int state) {
        if (this.nests == null) {
            this.nests = new ArrayList<>();
        }
        switch (state) {
            case LIST_ADD:
                this.nests.addAll(nests);
                break;
            case LIST_UPDATE:
                this.nests.clear();
                this.nests.addAll(nests);
                break;
            case LIST_DELETE:
                //TODO delete list
                break;
            default:
                break;
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nest_name)
        TextView nestName;
        @BindView(R.id.flag_master)
        ImageView flagMaster;
        @BindView(R.id.nest_item_progress)
        ProgressBar nestItemProgress;

        ViewHolder(View itemView, ItemOnClickListener itemOnClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener((view) -> itemOnClickListener.onItemClick(view, getAdapterPosition()));
        }

    }
}
