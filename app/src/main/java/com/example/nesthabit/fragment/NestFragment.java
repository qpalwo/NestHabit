package com.example.nesthabit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nesthabit.R;
import com.example.nesthabit.activity.NestContentActivity;
import com.example.nesthabit.activity.NestCreateActivity;
import com.example.nesthabit.adapter.NestContentRecyclerAdapter;
import com.example.nesthabit.base.BaseFragment;
import com.example.nesthabit.base.BaseView;
import com.example.nesthabit.base.ItemOnClickListener;
import com.example.nesthabit.model.bean.Nest;


import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dizzylay on 2018/4/21.
 */
public class NestFragment extends BaseFragment implements NestView {
    @BindView(R.id.nest_recycler_view)
    RecyclerView nestRecyclerView;
    @BindView(R.id.nest_float_button)
    FloatingActionButton nestFloatButton;
    NestContentRecyclerAdapter adapter;
    List<Nest> nestList;
    public static final String NEST = "NEST";

    Unbinder unbinder;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_nest;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        nestList = DataSupport.findAll(Nest.class);
        adapter = new NestContentRecyclerAdapter(new ItemOnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), NestContentActivity.class);
                intent.putExtra(NEST, nestList.get(position));
                startActivity(intent);
            }
        });
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        nestRecyclerView.setLayoutManager(manager);
        nestRecyclerView.setAdapter(adapter);
        adapter.changeData(nestList, NestContentRecyclerAdapter.LIST_UPDATE);
//        nestFraPresenter.setData();

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        upDateList();
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

    @Override
    public void setNestRecyclerData(List<Nest> list) {
        NestContentRecyclerAdapter adapter = (NestContentRecyclerAdapter) nestRecyclerView.getAdapter();
        adapter.changeData(list, NestContentRecyclerAdapter.LIST_UPDATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void upDateList() {
        if (adapter != null) {
            adapter.changeData(DataSupport.findAll(Nest.class), NestContentRecyclerAdapter.LIST_UPDATE);
        }
    }
}
