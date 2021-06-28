package com.example.tabactivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

@SuppressWarnings("deprecation")
public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
              return   new ChatFragment();
            case 1:
               return new UserFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
