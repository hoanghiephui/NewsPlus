package net.tichluy.ctinnhanh.ui.fragment.nguoiduatin.base;

/**
 * Created by Hoang Hiep on 8/1/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.tichluy.ctinnhanh.ui.fragment.MainFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    int mNumOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) throws NullPointerException{
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) throws NullPointerException{

        switch (position) {
            case 0:
                return MainFragment.newInstance("Trang Nhất");
            case 1:
                return MainFragment.newInstance("Tin Thời Sự");
            case 2:
                return MainFragment.newInstance("Tin Pháp Luật");
            case 3:
                return MainFragment.newInstance("Tin Đời Sống");
            case 4:
                return MainFragment.newInstance("Tin Thế Giới");
            default:
                return  MainFragment.newInstance("Trang Nhất");
        }
    }

    @Override
    public int getCount() throws NullPointerException{
        return mNumOfTabs;
    }

}
