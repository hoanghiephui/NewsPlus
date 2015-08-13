package net.tichluy.ctinnhanh.ui.fragment.vnreview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.tichluy.ctinnhanh.R;
import net.tichluy.ctinnhanh.ui.fragment.vnreview.base.ViewPagerAdapter;

/**
 * Created by Hoang Hiep on 8/2/2015.
 */
public class MainVnReview extends Fragment {
    public static String POSITION = "POSITION";
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        tabLayout = (TabLayout)rootView. findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.vnreview)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.danhgia)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tintuc)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tuvan)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.gocnhin)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.hoidap)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)rootView. findViewById(R.id.pager);
        final ViewPagerAdapter adapter = new ViewPagerAdapter
                (getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());

    }
}
