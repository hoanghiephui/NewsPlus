package net.tichluy.ctinnhanh.ui.fragment.thanhnien.base;

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
                return MainFragment.newInstance("Trang Chủ TN");
            case 1:
                return MainFragment.newInstance("Chính Trị");
            case 2:
                return MainFragment.newInstance("Quốc Phòng");
            case 3:
                return MainFragment.newInstance("Kinh Tế TN");
            case 4:
                return MainFragment.newInstance("Thế Giới TN");
            case 5:
                return MainFragment.newInstance("Game");
            case 6:
                return MainFragment.newInstance("Sức Khỏe TN");
            default:
                return  MainFragment.newInstance("Trang Chủ TN");
        }
    }

    @Override
    public int getCount() throws NullPointerException{
        return mNumOfTabs;
    }

}
