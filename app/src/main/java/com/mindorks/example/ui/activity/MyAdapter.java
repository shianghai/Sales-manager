package com.mindorks.example.ui.activity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.mindorks.example.ui.fragment.AddItemFragment;
import com.mindorks.example.ui.fragment.DeleteItemFragment;


public class MyAdapter extends FragmentStatePagerAdapter{
    Context context;
    int totalTabs;
    AddItemFragment addItemFragment;
    DeleteItemFragment deleteItemFragment;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        this.totalTabs = totalTabs;
        addItemFragment = new AddItemFragment();
        deleteItemFragment = new DeleteItemFragment();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:

                return addItemFragment;
            case 1:

                return deleteItemFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
