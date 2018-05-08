package com.example.nesthabit.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RecordFragment extends BaseFragment {
    @BindView(R.id.recording_and_share_content)
    EditText recordingAndShareContent;
    Unbinder unbinder;
    String record;

    @Override
    public int getContentViewId() {
        return R.layout.recording_and_share;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        setToolbarTitle("记录与分享");
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.release_recording)
    public void onViewClicked() {
        record = recordingAndShareContent.getText().toString();
        if (TextUtils.isEmpty(record)) {
            showToast("record can't be null", Toast.LENGTH_SHORT);
        } else {
            ReleaseSuccessFragment fragment = new ReleaseSuccessFragment();
            FragmentTransaction transaction = Objects.requireNonNull(getActivity())
                    .getSupportFragmentManager()
                    .beginTransaction();
            transaction.replace(R.id.punch_fragment_container, fragment)
                    .commit();
        }
    }
}
