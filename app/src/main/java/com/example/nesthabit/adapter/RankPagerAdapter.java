package com.example.nesthabit.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nesthabit.fragment.RankFragment;

import java.util.ArrayList;

/**
 * Created by dizzylay on 2018/4/19.
 */
public class RankPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<RankFragment> rankFragments;

    public RankPagerAdapter(FragmentManager fm, ArrayList<RankFragment> rankFragments) {
        super(fm);
        this.rankFragments = rankFragments;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "总打卡";
            case 1:
                return "连续打卡";
            default:
        }
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        return rankFragments.get(position);
    }

    @Override
    public int getCount() {
        return rankFragments.size();
    }
}
