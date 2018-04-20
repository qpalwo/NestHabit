package com.example.nesthabit.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nesthabit.base.BaseFragment;

import java.util.List;

public class HomePagerAdapter extends FragmentPagerAdapter {

    List<BaseFragment> fragments;
    String[] tabs;

    public HomePagerAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] tabs) {
        super(fm);
        this.fragments = fragments;
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
