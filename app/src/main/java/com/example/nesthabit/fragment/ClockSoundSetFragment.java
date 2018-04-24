package com.example.nesthabit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseFragment;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dizzylay on 2018/4/24.
 */
public class ClockSoundSetFragment extends BaseFragment {

    Unbinder unbinder;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_clock_sound;
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
        setToolbarTitle("铃声");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void setToolbarTitle(String title) {
        TextView toolbarTitle = Objects.requireNonNull(getActivity()).findViewById(R.id
                .toolbar_title);
        toolbarTitle.setText(title);
    }

}
