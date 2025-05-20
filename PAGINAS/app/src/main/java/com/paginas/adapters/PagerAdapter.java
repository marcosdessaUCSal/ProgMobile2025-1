package com.paginas.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.paginas.fragments.BackToTheFutureFragment;
import com.paginas.fragments.SchwarzeneggerFragment;
import com.paginas.fragments.StalloneFragment;

public class PagerAdapter extends FragmentStateAdapter {
    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SchwarzeneggerFragment();
            case 1:
                return new StalloneFragment();
            case 2:
                return new BackToTheFutureFragment();
            default:
                return new SchwarzeneggerFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
