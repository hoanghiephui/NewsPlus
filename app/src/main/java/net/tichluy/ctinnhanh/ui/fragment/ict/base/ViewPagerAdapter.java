package net.tichluy.ctinnhanh.ui.fragment.ict.base;

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
                return MainFragment.newInstance("Viễn Thông");
            case 1:
                return MainFragment.newInstance("Internet");
            case 2:
                return MainFragment.newInstance("CNTT");
            case 3:
                return MainFragment.newInstance("Kinh Doanh ICT");
            case 4:
                return MainFragment.newInstance("Thế Giới Số");
            case 5:
                return MainFragment.newInstance("Game ICT");
            case 6:
                return MainFragment.newInstance("Khởi Nghiệp");
            case 7:
                return MainFragment.newInstance("Công Nghệ 360");
            default:
                return  MainFragment.newInstance("Viễn Thông");
        }
    }

    @Override
    public int getCount() throws NullPointerException{
        return mNumOfTabs;
    }

}
