package com.example.nesthabit.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseFragment;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dizzylay on 2018/4/24.
 */
public class ClockSetFragment extends BaseFragment {

    Unbinder unbinder;


    @Override
    public int getContentViewId() {
        return R.layout.fragment_clock_set;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        if (rootView != null) {
            unbinder = ButterKnife.bind(this, rootView);
        }
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        setToolbarTitle("添加闹钟");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setToolbarTitle(String title) {
        TextView toolbarTitle = Objects.requireNonNull(getActivity()).findViewById(R.id
                .toolbar_title);
        toolbarTitle.setText(title);
    }

    @SuppressLint("ResourceType")
    @OnClick(R.id.clock_set_sound_item)
    public void onViewClicked() {
        ClockSoundSetFragment soundSetFragment = new ClockSoundSetFragment();
        FragmentTransaction transaction = Objects.requireNonNull(getActivity())
                .getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.clock_set_fragment_container, soundSetFragment)
                .addToBackStack("clock")
                .commit();
    }

    @OnClick({R.id.clock_days_0, R.id.clock_days_1, R.id.clock_days_2, R.id.clock_days_3, R.id
            .clock_days_4, R.id.clock_days_5, R.id.clock_days_6, R.id.clock_set_complete_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clock_days_0:
            case R.id.clock_days_1:
            case R.id.clock_days_2:
            case R.id.clock_days_3:
            case R.id.clock_days_4:
            case R.id.clock_days_5:
            case R.id.clock_days_6:
                if (view.isActivated()) {
                    view.setActivated(false);
                } else {
                    view.setActivated(true);
                }
                break;

            case R.id.clock_set_complete_button:
                break;
        }
    }
}
