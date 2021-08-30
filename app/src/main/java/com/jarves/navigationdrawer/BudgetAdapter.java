package com.jarves.navigationdrawer;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BudgetAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public BudgetAdapter(Context c, FragmentManager supportFragmentManager, int tabCount) {
        super(supportFragmentManager);
        context = c;
        this.totalTabs = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PendingFragment pendingFragment = new PendingFragment();
                return pendingFragment;
            case 1:
                CompletedFragment completedFragment = new CompletedFragment();
                return completedFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
