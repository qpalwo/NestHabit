package com.example.nesthabit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nesthabit.R;
import com.example.nesthabit.activity.ClockSetActivity;
import com.example.nesthabit.base.BaseFragment;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.model.bean.Clock;

import com.example.nesthabit.adapter.ClockRecyclerAdapter;
import com.example.nesthabit.widget.DeleteDialog;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ClockFragment extends BaseFragment {

    @BindView(R.id.clock_recycler_view)
    RecyclerView clockRecyclerView;
    @BindView(R.id.clock_float_button)
    FloatingActionButton clockFloatButton;

    ClockRecyclerAdapter clockRecyclerAdapter;
    Unbinder unbinder;

    List<Clock> clockList;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_clock;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        initRecycler();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateList();
    }


    private void initRecycler() {
        ItemOnClickListener itemOnClickListener = new ItemOnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.clock_item_checked:
                        break;
                    case R.id.clock_item_switch:
                        break;
                }
            }
        };
        clockRecyclerAdapter = new ClockRecyclerAdapter(itemOnClickListener);
        clockRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        clockRecyclerView.setAdapter(clockRecyclerAdapter);
        clockList = DataSupport.findAll(Clock.class);
        clockRecyclerAdapter.changeData(clockList, ClockRecyclerAdapter.LIST_UPDATE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @OnClick(R.id.clock_float_button)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), ClockSetActivity.class);
        startActivity(intent);
    }

    private void showDeleteDialog() {
        final DeleteDialog dialog = new DeleteDialog.Builder(getActivity())
                .heightDp(175)
                .widthDp(270)
                .style(R.style.Dialog)
                .cancelTouchOut(false)
                .view(R.layout.dialog_delete)
                .text("将该闹钟从列表中删除")
                .setDialogClickListener(new DeleteDialog.DialogClickListener() {
                    @Override
                    public void onCancelClicked() {
                    }

                    @Override
                    public void onDeleteClicked() {

                    }
                })
                .build();
        dialog.show();
    }

    public void updateList() {
        if (clockRecyclerAdapter != null){
            clockRecyclerAdapter.changeData(DataSupport.findAll(Clock.class),
                    ClockRecyclerAdapter.LIST_UPDATE);
        }
    }
}
