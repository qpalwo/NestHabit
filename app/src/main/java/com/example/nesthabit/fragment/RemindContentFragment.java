package com.example.nesthabit.fragment;

import android.os.Bundle;
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
public class RemindContentFragment extends BaseFragment {

    Unbinder unbinder;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_remind_content;
    }

    @Override
    public void onStart() {
        super.onStart();
        setToolbarTitle("提醒朋友");
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

    @OnClick({R.id.restart_record, R.id.tape_button, R.id.remind_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.restart_record:
                break;
            case R.id.tape_button:
                break;
            case R.id.remind_finish:
                break;
        }
    }
}
