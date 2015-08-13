package net.tichluy.ctinnhanh.ui.fragment.tinphong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.tichluy.ctinnhanh.R;
import net.tichluy.ctinnhanh.ui.fragment.tinphong.base.ViewPagerAdapter;

/**
 * Created by Hoang Hiep on 8/2/2015.
 */
public class MainTienPhong extends Fragment {
    public static String POSITION = "POSITION";
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        tabLayout = (TabLayout)rootView. findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.xahoiTP)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.kinhteTP)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.thegioiTP)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.gioitre)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.phapluatTP)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.giaitriTP)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.khoahocTp)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.nguoilinh)));
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
