package com.pereira.classificados.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.AttributeSet;

import com.pereira.classificados.fragment.ListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 19/01/2017.
 */

public class TabAdapter extends FragmentPagerAdapter {

    private List<String> mTitles;
    private List<Fragment> mFragments;

    public TabAdapter(FragmentManager fm) {
        super(fm);
        mTitles = new ArrayList<>();
        mFragments = new ArrayList<>();
    }

    public void add(String title, Fragment fragment){
        mTitles.add(title);
        mFragments.add(fragment);
    }

    //titulos
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void loadData(){
        for (Fragment f : mFragments){
            ((ListFragment) f).loadData();
        }
    }

}
