package net.tichluy.ctinnhanh.ui.fragment.kenh14.base;

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
                return MainFragment.newInstance("Home.");
            case 1:
                return MainFragment.newInstance("Star");
            case 2:
                return MainFragment.newInstance("Fashion");
            case 3:
                return MainFragment.newInstance("Ciné");
            case 4:
                return MainFragment.newInstance("TV Show");
            case 5:
                return MainFragment.newInstance("Thế Giới.");
            case 6:
                return MainFragment.newInstance("Sức Khỏe Giới Tính");
            case 7:
                return MainFragment.newInstance("Made By Me");
            case 8:
                return MainFragment.newInstance("2-Tek");
            default:
                return  MainFragment.newInstance("Home.");
        }
    }

    @Override
    public int getCount() throws NullPointerException{
        return mNumOfTabs;
    }

}
