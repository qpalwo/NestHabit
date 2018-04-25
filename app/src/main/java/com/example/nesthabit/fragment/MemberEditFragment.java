package com.example.nesthabit.fragment;

import com.example.nesthabit.R;
import com.example.nesthabit.base.BaseFragment;

/**
 * Created by dizzylay on 2018/4/25.
 */
public class MemberEditFragment extends BaseFragment {
    @Override
    public int getContentViewId() {
        return R.layout.fragment_member_edit;
    }

    @Override
    public void onStart() {
        super.onStart();
        setToolbarTitle("成员列表");
    }
}
