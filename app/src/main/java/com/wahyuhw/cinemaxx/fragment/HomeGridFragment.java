package com.wahyuhw.cinemaxx.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wahyuhw.cinemaxx.R;
import com.wahyuhw.cinemaxx.adapter.TabsGridAdapter;

public class HomeGridFragment extends Fragment {

    public HomeGridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout tabLayout = view.findViewById(R.id.home_tablayout);

        ViewPager viewPager = view.findViewById(R.id.home_viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TabsGridAdapter(getContext(), getChildFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);
    }
}
