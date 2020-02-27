package com.wahyuhw.cinemaxx.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wahyuhw.cinemaxx.R;
import com.wahyuhw.cinemaxx.fragment.FavoriteMoviesFragment;
import com.wahyuhw.cinemaxx.fragment.FavoriteTvShowsFragment;

public class TabsFavoriteAdapter extends FragmentPagerAdapter {
    private final Context mContext;

    public TabsFavoriteAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @StringRes
    private final int[] tabs = new int[]{
            R.string.tab_text_1_fav,
            R.string.tab_text_2_fav
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FavoriteMoviesFragment();
                break;
            case 1:
                fragment = new FavoriteTvShowsFragment();
                break;
        }
        return fragment;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(tabs[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
