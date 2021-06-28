package com.example.tabactivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tabactivity.fragments.Chat;
import com.example.tabactivity.fragments.HomeFragment;
import com.example.tabactivity.fragments.Logout;

@SuppressWarnings("deprecation")
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                return new HomeFragment();
            case 1:
                return new Chat();
            case 2:
                return new Logout();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }
}
