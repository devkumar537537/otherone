package com.example.tabsactivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tabsactivity.fragments.Chat;
import com.example.tabsactivity.fragments.Home;
import com.example.tabsactivity.fragments.User;

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
              return new Home();
            case 1:
              return new Chat();
            case 2:
              return new User();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
