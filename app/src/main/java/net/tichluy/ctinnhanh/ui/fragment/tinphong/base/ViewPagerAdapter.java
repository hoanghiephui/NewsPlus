package net.tichluy.ctinnhanh.ui.fragment.tinphong.base;

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
                return MainFragment.newInstance("Xã Hội TP");
            case 1:
                return MainFragment.newInstance("Kinh Tế TP");
            case 2:
                return MainFragment.newInstance("Thế Giới TP");
            case 3:
                return MainFragment.newInstance("Giới Trẻ");
            case 4:
                return MainFragment.newInstance("Pháp Luật TP");
            case 5:
                return MainFragment.newInstance("Giải Trí TP");
            case 6:
                return MainFragment.newInstance("Khoa Học TP");
            case 7:
                return MainFragment.newInstance("Người Lính");
            default:
                return  MainFragment.newInstance("Xã Hội TP");
        }
    }

    @Override
    public int getCount() throws NullPointerException{
        return mNumOfTabs;
    }

}
