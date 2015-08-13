package net.tichluy.ctinnhanh.ui.fragment.dantri.base;

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
                return MainFragment.newInstance("Trang Chủ");
            case 1:
                return MainFragment.newInstance("Xã Hội");
            case 2:
                return MainFragment.newInstance("Thế Giới");
            case 3:
                return MainFragment.newInstance("Giáo Dục");
            case 4:
                return MainFragment.newInstance("Kinh Doanh");
            case 5:
                return MainFragment.newInstance("Giải Trí");
            case 6:
                return MainFragment.newInstance("Pháp Luật");
            case 7:
                return MainFragment.newInstance("Sức Khỏe");
            case 8:
                return MainFragment.newInstance("Xe");
            case 9:
                return MainFragment.newInstance("Sức Mạnh Số");
            default:
                return MainFragment.newInstance("Trang Chủ");
        }
    }

    @Override
    public int getCount() throws NullPointerException{
        return mNumOfTabs;
    }

}
