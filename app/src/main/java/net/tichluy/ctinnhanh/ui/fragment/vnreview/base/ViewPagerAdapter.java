package net.tichluy.ctinnhanh.ui.fragment.vnreview.base;

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
                return MainFragment.newInstance("VnReview");
            case 1:
                return MainFragment.newInstance("Đánh Giá");
            case 2:
                return MainFragment.newInstance("Tin Tức");
            case 3:
                return MainFragment.newInstance("Tư Vấn");
            case 4:
                return MainFragment.newInstance("Góc Nhìn");
            case 5:
                return MainFragment.newInstance("Hỏi Đáp");
            default:
                return  MainFragment.newInstance("VnReview");
        }
    }

    @Override
    public int getCount() throws NullPointerException{
        return mNumOfTabs;
    }

}
