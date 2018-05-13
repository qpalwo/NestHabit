package com.example.nesthabit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.model.DateUtil;
import com.example.nesthabit.model.bean.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> messageList;
    private ItemOnClickListener itemOnClickListener;

    public MessageAdapter(ItemOnClickListener listener) {
        this.itemOnClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == Message.COMMON || viewType == Message.OTHER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        } else if (viewType == Message.OWN) {

        }
        return new ViewHolder(view, itemOnClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.messageContentText.setText(message.getContent());
        holder.messageTimeText.setText(DateUtil.formatDate(message.getTime()));
        holder.messageUserName.setText(message.getUserName());
    }

    @Override
    public int getItemCount() {
        if (messageList != null) {
            return messageList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void changeData(List<Message> messages) {
        if (messageList == null) {
            messageList = new ArrayList<>();
        }
        messageList.clear();
        messageList.addAll(messages);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message_content_text)
        TextView messageContentText;
        @BindView(R.id.message_time_text)
        TextView messageTimeText;
        @BindView(R.id.message_user_name)
        TextView messageUserName;
        @BindView(R.id.message_user_avatar)
        ImageView messageUserAvatar;

        ViewHolder(View itemView, ItemOnClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener((view) -> listener.onItemClick(view, getAdapterPosition()));
        }
    }
}
