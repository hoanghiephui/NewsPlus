package net.tichluy.ctinnhanh.ui.fragment.doisongpl.base;

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
                return MainFragment.newInstance("Tin Nhất");
            case 1:
                return MainFragment.newInstance("Pháp Luật Đời Sống");
            case 2:
                return MainFragment.newInstance("Thế Giới Đời Sống");
            case 3:
                return MainFragment.newInstance("Thể Thao Đời Sống");
            case 4:
                return MainFragment.newInstance("Địa Phương");
            case 5:
                return MainFragment.newInstance("An Ninh TV");
            default:
                return  MainFragment.newInstance("Tin Nhất");
        }
    }

    @Override
    public int getCount() throws NullPointerException{
        return mNumOfTabs;
    }

}
