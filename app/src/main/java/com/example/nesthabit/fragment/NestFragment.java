package com.example.nesthabit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nesthabit.R;
import com.example.nesthabit.activity.NestCreateActivity;
import com.example.nesthabit.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dizzylay on 2018/4/21.
 */
public class NestFragment extends BaseFragment {
    @BindView(R.id.nest_recycler_view)
    RecyclerView nestRecyclerView;
    @BindView(R.id.nest_float_button)
    FloatingActionButton nestFloatButton;
    Unbinder unbinder;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_nest;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.nest_float_button)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), NestCreateActivity.class);
        startActivity(intent);
    }
}
