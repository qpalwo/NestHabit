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
import com.example.nesthabit.activity.HomeActivity;
import com.example.nesthabit.base.BaseFragment;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.presenter.ClockFraPresenter;
import com.example.nesthabit.adapter.ClockRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ClockFragment extends BaseFragment implements ClockView {
    @BindView(R.id.clock_recycler_view)
    RecyclerView clockRecyclerView;
    @BindView(R.id.clock_float_button)
    FloatingActionButton clockFloatButton;
    Unbinder unbinder;
    ClockFraPresenter clockFraPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_clock;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        clockFraPresenter = new ClockFraPresenter();
        clockFraPresenter.attachView(this);
        initRecycler();
        return rootView;
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
        ClockRecyclerAdapter clockRecyclerAdapter = new ClockRecyclerAdapter(itemOnClickListener);
        clockRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        clockRecyclerView.setAdapter(clockRecyclerAdapter);
        //TODO  更新数据
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        clockFraPresenter.detachView();
    }

    @OnClick(R.id.clock_float_button)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), ClockSetActivity.class);
        startActivity(intent);
    }
}
