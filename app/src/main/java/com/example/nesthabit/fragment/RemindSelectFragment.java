package com.example.nesthabit.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseFragment;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dizzylay on 2018/4/25.
 */
public class RemindSelectFragment extends BaseFragment {
    Unbinder unbinder;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_remind_select;
    }

    @Override
    public void onStart() {
        super.onStart();
        setToolbarTitle("选择");
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

    @OnClick({R.id.remind_select_friend_item, R.id.remind_next_step})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.remind_select_friend_item:
                break;
            case R.id.remind_next_step:
                RemindContentFragment remindContentFragment = new RemindContentFragment();
                FragmentTransaction transaction = Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.remind_friend_fragment_container, remindContentFragment)
                        .addToBackStack("remind")
                        .commit();
                break;
        }
    }
}
