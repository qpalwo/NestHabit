package com.example.nesthabit.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.nesthabit.R;
import com.example.nesthabit.adapter.MessageAdapter;
import com.example.nesthabit.base.BaseFragment;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.model.bean.Message;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dizzylay on 2018/4/19.
 */
public class PunchAndCommunicateFragment extends BaseFragment {
    @BindView(R.id.fragment_punch_recycler_view)
    RecyclerView fragmentPunchRecyclerView;
    @BindView(R.id.message_edit_text)
    EditText messageEditText;
    @BindView(R.id.message_send_item)
    LinearLayout messageSendItem;
    Unbinder unbinder;
    private List<Message> messages;
    private MessageAdapter adapter;
    private PunchFragmentCallback punchFragmentCallback;
    @Override
    public int getContentViewId() {
        return R.layout.fragment_punch;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        punchFragmentCallback = (PunchFragmentCallback) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initView() {
        messages = punchFragmentCallback.getMessages();
        adapter = new MessageAdapter(new ItemOnClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        adapter.changeData(messages);
        fragmentPunchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentPunchRecyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.message_send_button)
    public void onViewClicked() {
    }

    public interface PunchFragmentCallback {
        List<Message> getMessages();
    }
}
