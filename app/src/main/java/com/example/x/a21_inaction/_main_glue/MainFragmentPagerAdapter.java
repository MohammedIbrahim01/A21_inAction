package com.example.x.a21_inaction._main_glue;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    //must implement constructor
    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    //to grab the right fragment for current position
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


    //to notify the adapter with the number of fragments it will manipulate
    @Override
    public int getCount() {
        return titles.size();
    }


    //to set the tab text to the right title
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }


    //method to use to add fragments with their title
    public void addFragment(Fragment fragment, String title) {

        fragmentList.add(fragment);
        titles.add(title);

    }
}
