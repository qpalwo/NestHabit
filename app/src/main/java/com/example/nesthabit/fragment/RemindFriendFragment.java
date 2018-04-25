package com.example.nesthabit.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dizzylay on 2018/4/25.
 */
public class RemindFriendFragment extends BaseFragment {

    Unbinder unbinder;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_remind_friend;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        setToolbarTitle("提醒朋友");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.add_remind_float_button)
    public void onViewClicked() {
        RemindSelectFragment selectFragment = new RemindSelectFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.remind_friend_fragment_container,selectFragment)
                .addToBackStack("remind")
                .commit();
    }
}
