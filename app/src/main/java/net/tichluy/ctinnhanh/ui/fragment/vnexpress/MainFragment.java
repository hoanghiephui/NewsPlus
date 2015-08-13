package net.tichluy.ctinnhanh.ui.fragment.vnexpress;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.tichluy.ctinnhanh.R;
import net.tichluy.ctinnhanh.ui.fragment.vnexpress.base.ViewPagerAdapter;

/**
 * Created by Hoang Hiep on 8/1/2015.
 */
public class MainFragment extends Fragment {
    public static String POSITION = "POSITION";
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        tabLayout = (TabLayout)rootView. findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.home_vn)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.thoisu)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.doisong)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.thegioi)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.kinhdoanh)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.giaitri)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.phapluat)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.khoahoc)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.xe)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tamsu)));
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
