package com.example.nesthabit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.example.nesthabit.R;
import com.example.nesthabit.activity.RemindFriendActivity;
import com.example.nesthabit.base.BaseFragment;
import com.example.nesthabit.widget.DeleteDialog;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dizzylay on 2018/4/25.
 */
public class NestDetailFragment extends BaseFragment {

    private static final String TAG = "NestDetailFragment";
    Unbinder unbinder;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_nest_detail;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
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
        setToolbarTitle("早起的鸟儿有虫吃");
        initView();
    }

    private void initView() {
        ViewStub viewStub = Objects.requireNonNull(getActivity()).findViewById(R.id.detail_creator);
        if (viewStub != null) {
            View inflatedView = viewStub.inflate();
        }
        TextView exitText = getActivity().findViewById(R.id.detail_exit_text);
        exitText.setText("解散鸟窝");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.detail_member_number_item, R.id.detail_remind_friend_item, R.id
            .detail_exit_item})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.detail_member_number_item:
                MemberEditFragment memberEditFragment = new MemberEditFragment();
                FragmentTransaction transaction = Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.nest_detail_fragment_container, memberEditFragment)
                        .addToBackStack("nest")
                        .commit();
                break;
            case R.id.detail_remind_friend_item:
                Intent intent = new Intent(getActivity(), RemindFriendActivity.class);
                startActivity(intent);
                break;
            case R.id.detail_exit_item:
                showDeleteDialog();
                break;
        }
    }

    private void showDeleteDialog() {
        final DeleteDialog dialog = new DeleteDialog.Builder(getActivity())
                .heightDp(175)
                .widthDp(270)
                .style(R.style.Dialog)
                .cancelTouchOut(false)
                .view(R.layout.dialog_delete)
                .text("将该鸟窝解散")
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
}
